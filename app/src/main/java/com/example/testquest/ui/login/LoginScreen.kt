package com.example.testquest.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testquest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                LoginAction.NavToPaymentsScreen -> {
                    navController.navigate("payments")
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            TextField(
                value = uiState.login,
                onValueChange = { loginText ->
                    viewModel.onLoginChanged(loginText)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.login_text_field_hint)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = uiState.password,
                onValueChange = { password ->
                    viewModel.onPasswordChanged(password)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password_text_field_hint)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = {
                    viewModel.onLogIn()
                }
            ) {
                Text(
                    text = stringResource(R.string.login_button_text)
                )
            }
            
            AnimatedVisibility(visible = uiState.isError) {
                Text(
                    text = stringResource(id = R.string.login_error_text)
                )
            }
        }
    }
}