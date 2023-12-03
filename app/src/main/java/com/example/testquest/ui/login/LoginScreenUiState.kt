package com.example.testquest.ui.login

import javax.annotation.concurrent.Immutable

@Immutable
data class LoginScreenUiState(
    val login: String,
    val password: String,
    val isError: Boolean
) {
    companion object {
        val default = LoginScreenUiState(
            login = "",
            password = "",
            isError = false
        )
    }
}
