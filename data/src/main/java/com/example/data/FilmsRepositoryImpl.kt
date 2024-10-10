package com.example.data

import com.example.api.FilmsApi
import com.example.api.FilmsResult
import com.example.data.mappers.FilmDBOtoDomainMapper
import com.example.data.mappers.FilmDTOtoDBOMapper
import com.example.data.mappers.FilmDTOtoDomainMapper
import com.example.database.FilmsDatabase
import com.example.domain.FilmsRepository
import com.example.domain.error.DataError
import com.example.domain.models.Film
import com.example.domain.models.FilmsLoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import java.net.UnknownHostException

internal class FilmsRepositoryImpl(
    private val db: FilmsDatabase,
    private val api: FilmsApi,
    private val filmDBOtoDomainMapper: FilmDBOtoDomainMapper,
    private val filmDTOtoDomainMapper: FilmDTOtoDomainMapper,
    private val filmDTOtoDBOMapper: FilmDTOtoDBOMapper,
    private val mergeStrategy: FilmsLoadStatusMergeStrategy,
) : FilmsRepository {
    override fun films(): Flow<FilmsLoadStatus> {
        val cache = cacheFlow()

        val server = serverFlow()

        return cache.combine(server, mergeStrategy::merge).flowOn(Dispatchers.IO)
    }

    private fun serverFlow(): Flow<FilmsLoadStatus> {
        val apiRequestStartFlow = flow {
            emit(FilmsLoadStatus.Loading(emptyList()))
        }.flowOn(Dispatchers.IO)

        val apiRequestFlow = flow {
            val apiResponse = api.fetch()
            emit(
                when (apiResponse) {
                    is FilmsResult.Error -> {
                        when (apiResponse.exception) {
                            is UnknownHostException -> FilmsLoadStatus.Error(
                                films = emptyList(),
                                error = DataError.NetworkError.NO_INTERNET
                            )

                            else -> {
                                FilmsLoadStatus.Error(
                                    films = emptyList(),
                                    error = DataError.NetworkError.UNKNOWN
                                )
                            }
                        }
                    }

                    is FilmsResult.Success -> {
                        db.filmsDao.clearAndInsert(apiResponse.films.map { filmDTO ->
                            filmDTOtoDBOMapper.map(filmDTO)
                        })
                        FilmsLoadStatus.Success(films = apiResponse.films.map { filmDTO ->
                            filmDTOtoDomainMapper.map(filmDTO)
                        })
                    }
                }
            )
        }.flowOn(Dispatchers.IO)

        val server = merge(apiRequestStartFlow, apiRequestFlow).flowOn(Dispatchers.IO)
        return server
    }

    private fun cacheFlow(): Flow<FilmsLoadStatus> {
        val dbRequestStartFlow = flow {
            emit(FilmsLoadStatus.Loading(emptyList()))
        }.flowOn(Dispatchers.IO)
        val dbRequestFlow = flow {
            val films = db.filmsDao.films()
            emit(FilmsLoadStatus.Success(films.map { filmDBO ->
                filmDBOtoDomainMapper.map(filmDBO)
            }))
        }.flowOn(Dispatchers.IO)
        val cache = merge(dbRequestStartFlow, dbRequestFlow).flowOn(Dispatchers.IO)
        return cache
    }

    override fun film(id: Long): Flow<Film> {
        return flow {
            emit(filmDBOtoDomainMapper.map(db.filmsDao.film(id)))
        }.flowOn(Dispatchers.IO)
    }
}