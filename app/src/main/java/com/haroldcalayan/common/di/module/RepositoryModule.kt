package com.haroldcalayan.common.di.module

import android.content.Context
import com.haroldcalayan.data.MovieRepository
import com.haroldcalayan.data.MovieRepositoryImpl
import com.haroldcalayan.data.source.local.ITunesMoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(context: Context, appDatabase: ITunesMoviesDatabase) : MovieRepository {
        return MovieRepositoryImpl(context, appDatabase)
    }
}