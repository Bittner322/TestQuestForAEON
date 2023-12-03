package com.example.testquest.di

import android.content.SharedPreferences
import com.example.testquest.data.network.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val SHARED_PREFS_TOKEN = "token"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkhttp(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(Interceptor { chain ->
                val token = sharedPreferences.getString(SHARED_PREFS_TOKEN, "").orEmpty()

                val paymentsRequest = chain.request().newBuilder()
                    .addHeader("token", token)
                    .build()

                chain.proceed(paymentsRequest)
            }).build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = true
        encodeDefaults = true
        isLenient = true
        prettyPrint = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(
        okHttp: OkHttpClient,
        json: Json
    ): Retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl("https://easypay.world/api-test/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)
}