package com.haroldcalayan.common.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.haroldcalayan.data.source.local.ITunesMoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): ITunesMoviesDatabase {
        return Room.databaseBuilder(
            context,
            ITunesMoviesDatabase::class.java,
            "itunes_database"
        ).build()
    }
}