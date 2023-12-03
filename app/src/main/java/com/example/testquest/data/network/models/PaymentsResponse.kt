package com.example.testquest.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentsResponse(
    @SerialName("success")
    val success: String,
    @SerialName("response")
    val response: List<Response>?,
    @SerialName("error")
    val error: Error?
) {
    @Serializable
    data class Response(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("amount")
        val amount: String? = null,
        @SerialName("created")
        val created: Int? = null
    )
    @Serializable
    data class Error(
        @SerialName("error_code")
        val error_code: Int,
        @SerialName("error_msg")
        val error_msg: String
    )
}