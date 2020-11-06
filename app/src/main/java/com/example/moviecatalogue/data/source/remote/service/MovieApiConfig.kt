package com.example.moviecatalogue.data.source.remote.service

import com.example.moviecatalogue.utils.ConstantValue.API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiConfig {
    private var retrofit: Retrofit? = null
    fun getApiService(): MovieApiService? {
        if (retrofit == null) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit?.create(MovieApiService::class.java)
    }
}