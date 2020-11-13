package com.haroldcalayan.common.di.component

import com.haroldcalayan.common.di.module.AppModule
import com.haroldcalayan.common.di.module.NetworkModule
import com.haroldcalayan.common.di.module.RepositoryModule
import com.haroldcalayan.data.MovieRepositoryImpl
import com.haroldcalayan.feature.detail.DetailViewModel
import com.haroldcalayan.feature.master.MasterViewModel
import com.haroldcalayan.feature.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ AppModule::class, NetworkModule::class, RepositoryModule::class ])
interface AppComponent {

    fun inject(viewModel: SplashViewModel)
    fun inject(viewModel: MasterViewModel)
    fun inject(viewModel: DetailViewModel)

    fun inject(repository: MovieRepositoryImpl)
}