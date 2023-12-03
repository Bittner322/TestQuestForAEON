package com.example.testquest.ui.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testquest.data.network.models.PaymentsResponse
import com.example.testquest.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _paymentsFlow = MutableStateFlow(emptyList<PaymentsResponse.Response>())
    val paymentsFlow = _paymentsFlow.asStateFlow()

    private val _uiAction = Channel<PaymentsAction>()
    val uiAction = _uiAction.receiveAsFlow()

    init {
        loadPayments()
    }

    private fun loadPayments() {
        viewModelScope.launch {
            _paymentsFlow.emit(userRepository.getPayments())
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            userRepository.clearUserData()
            _uiAction.send(PaymentsAction.NavToLoginScreen)
        }
    }
}

sealed class PaymentsAction {
    data object NavToLoginScreen: PaymentsAction()
}