package com.haroldcalayan.common.base

import com.haroldcalayan.ITunesMoviesApplication
import com.haroldcalayan.data.MovieRepository

abstract class BaseRepository<T> {

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MovieRepository -> ITunesMoviesApplication.instance.appComponent.inject(this)
        }
    }

    abstract suspend fun getAllItems(): List<T>

    abstract suspend fun getCachedItems(): List<T>

    abstract suspend fun getSomeItems(limit: Int, offset: Int): List<T>

    abstract suspend fun insertItem(t: T)

    abstract suspend fun insertItem(list: List<T>)

    abstract suspend fun updateItem(t: T)

    abstract suspend fun deleteItem()
}