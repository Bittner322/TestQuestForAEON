package com.example.testquest.data.repositories

import android.content.SharedPreferences
import com.example.testquest.data.network.NetworkService
import com.example.testquest.data.network.models.PaymentsResponse
import com.example.testquest.data.network.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val SHARED_PREFS_TOKEN = "token"

class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun getToken(
        login: String,
        password: String
    ): String {
        return runCatching {
            networkService.login(
                User(
                    login = login,
                    password = password
                )
            ).response.token
        }.getOrDefault("")
    }

    suspend fun getPayments(): List<PaymentsResponse.Response> {
        return withContext(Dispatchers.IO) {
            networkService.getPaymentsList().response ?: emptyList()
        }
    }

    fun isTokenExist(): Boolean {
        val token = sharedPreferences.getString(SHARED_PREFS_TOKEN, null)
        return token != null
    }


    suspend fun setTokenValue(token: String) {
        return withContext(Dispatchers.IO) {
            with(sharedPreferences.edit()) {
                putString(SHARED_PREFS_TOKEN, token)
                apply()
            }
        }
    }

    suspend fun clearUserData() {
        return withContext(Dispatchers.IO) {
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
        }
    }
}