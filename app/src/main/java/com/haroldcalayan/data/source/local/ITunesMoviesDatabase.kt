package com.haroldcalayan.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haroldcalayan.data.model.Movie
import com.haroldcalayan.data.source.remote.converter.DateConverter

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ITunesMoviesDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}