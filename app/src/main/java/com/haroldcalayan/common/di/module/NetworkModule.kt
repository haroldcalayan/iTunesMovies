package com.haroldcalayan.common.di.module

import com.haroldcalayan.BuildConfig
import com.haroldcalayan.data.source.remote.ApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule(private val baseAppUrl: String) {

    @Provides
    @Singleton
    fun provideHttpClient(okHttpBuilder: OkHttpClient.Builder) = okHttpBuilder.build()

    @Provides
    @Singleton
    fun provideHttpClientBuilder(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        var okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        return okHttpBuilder
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideApiClient(client: OkHttpClient.Builder) = ApiClient(baseAppUrl, client)

}