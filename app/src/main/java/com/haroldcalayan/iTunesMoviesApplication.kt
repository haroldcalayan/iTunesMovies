package com.haroldcalayan

import android.app.Application
import com.haroldcalayan.common.di.component.AppComponent
import com.haroldcalayan.common.di.component.DaggerAppComponent
import com.haroldcalayan.common.di.module.AppModule
import com.haroldcalayan.common.di.module.NetworkModule
import com.haroldcalayan.common.di.module.RepositoryModule
import timber.log.Timber

class ITunesMoviesApplication : Application() {

    val appComponent: AppComponent by lazy { initializeComponent() }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLog()
    }

    private fun initializeComponent() =  DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .networkModule(NetworkModule(BuildConfig.BASE_APP_URL))
        .repositoryModule(RepositoryModule())
        .build()

    private fun initLog() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: ITunesMoviesApplication
    }
}