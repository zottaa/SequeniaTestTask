package com.example.data

import com.example.domain.models.FilmsLoadStatus


internal interface MergeStrategy<E> {
    fun merge(left: E, right: E): E
}

internal interface FilmsLoadStatusMergeStrategy : MergeStrategy<FilmsLoadStatus>

internal class FilmsLoadStatusMergeStrategyImpl : FilmsLoadStatusMergeStrategy {
    @Suppress("CyclomaticComplexMethod")
    override fun merge(
        left: FilmsLoadStatus,
        right: FilmsLoadStatus
    ): FilmsLoadStatus {
        return when {
            left is FilmsLoadStatus.Success && right is FilmsLoadStatus.Success -> merge(
                left,
                right
            )

            left is FilmsLoadStatus.Success && right is FilmsLoadStatus.Loading -> merge(
                left,
                right
            )

            left is FilmsLoadStatus.Success && right is FilmsLoadStatus.Error -> merge(
                left,
                right
            )

            left is FilmsLoadStatus.Loading && right is FilmsLoadStatus.Success -> merge(
                left,
                right
            )

            left is FilmsLoadStatus.Loading && right is FilmsLoadStatus.Loading -> merge(
                left,
                right
            )

            left is FilmsLoadStatus.Loading && right is FilmsLoadStatus.Error -> merge(
                left,
                right
            )

            left is FilmsLoadStatus.Error -> merge(
                left,
                right
            )

            else -> error("Unimplemented branch")
        }
    }

    private fun merge(
        cache: FilmsLoadStatus.Success,
        server: FilmsLoadStatus.Success
    ): FilmsLoadStatus {
        return FilmsLoadStatus.Success(server.films)
    }

    private fun merge(
        cache: FilmsLoadStatus.Success,
        server: FilmsLoadStatus.Loading
    ): FilmsLoadStatus {
        return FilmsLoadStatus.Loading(cache.films)
    }

    private fun merge(
        cache: FilmsLoadStatus.Success,
        server: FilmsLoadStatus.Error
    ): FilmsLoadStatus {
        return FilmsLoadStatus.Error(cache.films, server.error)
    }

    private fun merge(
        cache: FilmsLoadStatus.Loading,
        server: FilmsLoadStatus.Success
    ): FilmsLoadStatus {
        return FilmsLoadStatus.Success(server.films)
    }

    private fun merge(
        cache: FilmsLoadStatus.Loading,
        server: FilmsLoadStatus.Loading
    ): FilmsLoadStatus {
        return when {
            cache.films.isNotEmpty() -> FilmsLoadStatus.Loading(cache.films)
            server.films.isNotEmpty() -> FilmsLoadStatus.Loading(server.films)
            else -> FilmsLoadStatus.Loading(emptyList())
        }
    }

    private fun merge(
        cache: FilmsLoadStatus.Loading,
        server: FilmsLoadStatus.Error
    ): FilmsLoadStatus {
        return when {
            cache.films.isNotEmpty() -> FilmsLoadStatus.Error(cache.films, server.error)
            server.films.isNotEmpty() -> FilmsLoadStatus.Error(server.films, server.error)
            else -> FilmsLoadStatus.Error(emptyList(), server.error)
        }
    }

    private fun merge(
        cache: FilmsLoadStatus.Error,
        server: FilmsLoadStatus.Success
    ): FilmsLoadStatus {
        return FilmsLoadStatus.Error(server.films, cache.error)
    }

    private fun merge(
        cache: FilmsLoadStatus.Error,
        server: FilmsLoadStatus.Loading
    ): FilmsLoadStatus {
        return when {
            cache.films.isNotEmpty() -> FilmsLoadStatus.Error(cache.films, cache.error)
            server.films.isNotEmpty() -> FilmsLoadStatus.Error(server.films, cache.error)
            else -> FilmsLoadStatus.Error(emptyList(), cache.error)
        }
    }

    private fun merge(
        cache: FilmsLoadStatus.Error,
        server: FilmsLoadStatus.Error
    ): FilmsLoadStatus {
        return when {
            cache.films.isNotEmpty() -> FilmsLoadStatus.Error(
                cache.films,
                server.error
            )

            server.films.isNotEmpty() -> FilmsLoadStatus.Error(
                server.films,
                server.error
            )

            else -> FilmsLoadStatus.Error(
                emptyList(),
                server.error
            )
        }
    }
}
