package com.example.blindapp.di

import com.example.blindapp.util.DateUtil
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat(DateUtil.serverDateFormat.toPattern()) // 요렇게 해주면 서버에서 날짜 형식이 우리가 지정했던 패턴으로 자동 변환되어 받아와질것임
                .create()
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.31.16:3030/api/v1/fastcampus/chapter8/")
            .addConverterFactory(gsonConverterFactory)
            .client(client.build())
            .build()
    }

}