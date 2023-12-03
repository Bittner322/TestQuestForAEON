package com.example.testquest.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String
)
