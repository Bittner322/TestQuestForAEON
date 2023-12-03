package com.example.testquest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testquest.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiAction = Channel<LoginAction>()
    val uiAction = _uiAction.receiveAsFlow()

    private val _uiState = MutableStateFlow(LoginScreenUiState.default)
    val uiState = _uiState.asStateFlow()

    fun onLoginChanged(login: String) {
        _uiState.update {
            it.copy(
                login = login
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onLogIn() {
        viewModelScope.launch {
            val token = userRepository.getToken(
                login = uiState.value.login,
                password = uiState.value.password
            )
            if (token != "") {
                userRepository.setTokenValue(token)
                _uiState.update {
                    it.copy(
                        isError = false
                    )
                }
                _uiAction.send(LoginAction.NavToPaymentsScreen)
            } else {
                _uiState.update {
                    it.copy(
                        isError = true
                    )
                }
            }
        }
    }
}

sealed class LoginAction {
    data object NavToPaymentsScreen: LoginAction()
}