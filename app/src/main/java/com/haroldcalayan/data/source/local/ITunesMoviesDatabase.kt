package com.haroldcalayan.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haroldcalayan.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class ITunesMoviesDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}