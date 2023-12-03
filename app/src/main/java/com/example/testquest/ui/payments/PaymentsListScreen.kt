package com.example.testquest.ui.payments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testquest.R

@Composable
fun PaymentsListScreen(
    navController: NavController,
    viewModel: PaymentsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                PaymentsAction.NavToLoginScreen -> {
                    navController.navigate("login")
                }
            }
        }
    }

    val payments by viewModel.paymentsFlow.collectAsState()
    
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(payments) { payment ->
            PaymentsListItem(payment = payment)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onLogoutClick()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.payments_logout)
                )
            }
        }
    }
}