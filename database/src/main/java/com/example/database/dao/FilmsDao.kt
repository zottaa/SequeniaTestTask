package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.FilmDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFilms(films: List<FilmDBO>)

    @Query("DELETE FROM films")
    suspend fun clear()

    @Query("SELECT * FROM films")
    suspend fun films(): List<FilmDBO>

    @Query("SELECT * FROM films WHERE id = :id")
    suspend fun film(id: Long): FilmDBO
}