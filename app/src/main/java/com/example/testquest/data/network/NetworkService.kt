package com.example.testquest.data.network

import com.example.testquest.data.network.models.PaymentsResponse
import com.example.testquest.data.network.models.TokenResponse
import com.example.testquest.data.network.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface NetworkService {
    @Headers("app-key: 12345", "v: 1")
    @POST("login")
    suspend fun login(
        @Body user: User
    ): TokenResponse

    @Headers("app-key: 12345", "v: 1")
    @GET("payments")
    suspend fun getPaymentsList(): PaymentsResponse
}