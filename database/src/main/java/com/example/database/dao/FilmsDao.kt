package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.FilmDBO
import java.util.concurrent.Flow

@Dao
interface FilmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFilms(films: List<FilmDBO>)

    @Query("DELETE FROM films")
    suspend fun clear()

    @Query("SELECT * FROM films")
    fun films(): Flow<>
}