package com.example.blindapp.di

import com.example.blindapp.data.source.remote.api.ContentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providesContentService(retrofit: Retrofit): ContentService =
        retrofit.create(ContentService::class.java)
}