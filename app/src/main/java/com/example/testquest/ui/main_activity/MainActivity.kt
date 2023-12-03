package com.example.testquest.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testquest.ui.login.LoginScreen
import com.example.testquest.ui.payments.PaymentsListScreen
import com.example.testquest.ui.theme.TestQuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestQuestTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = if (viewModel.isTokenExist()) "payments" else "login"
                ) {
                    composable("login") {
                        LoginScreen(navController = navController)
                    }
                    composable("payments") {
                        PaymentsListScreen(navController = navController)
                    }
                }
            }
        }
    }
}