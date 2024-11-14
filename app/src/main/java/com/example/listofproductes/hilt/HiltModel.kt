package com.example.listofproductes.hilt

import android.content.Context
import com.example.listofproductes.retrofit.ApiService
import com.srinivash.task.retrofit.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModel {

    @Provides
    @Singleton
    fun providerContext(@ApplicationContext context: Context): Context = context







    @Provides
    @Singleton
    fun provideRetrofit(context: Context): ApiService {
        val client = OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder().baseUrl("https://dummyapi.online/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }
}
