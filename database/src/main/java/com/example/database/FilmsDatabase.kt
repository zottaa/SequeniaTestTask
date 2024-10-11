package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.dao.FilmsDao
import com.example.database.models.FilmDBO
import com.example.database.utils.StringListConverter

@Database(entities = [FilmDBO::class], version = 1)
@TypeConverters(StringListConverter::class)
internal abstract class FilmsRoomDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}

class FilmsDatabase internal constructor(
    private val db: FilmsRoomDatabase
) {
    val filmsDao: FilmsDao
        get() = db.filmsDao()
}

fun ProvideDatabase(applicationContext: Context): FilmsDatabase {
    val db = Room.databaseBuilder(
        applicationContext.applicationContext,
        FilmsRoomDatabase::class.java,
        "films_database"
    ).build()
    return FilmsDatabase(db)
}