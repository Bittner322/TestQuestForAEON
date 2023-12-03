package com.example.testquest.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("success")
    val success: String,
    @SerialName("response")
    val response: Response
) {
    @Serializable
    data class Response(
        @SerialName("token")
        val token: String
    )
}