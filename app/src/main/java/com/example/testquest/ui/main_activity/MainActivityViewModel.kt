package com.example.testquest.ui.main_activity

import androidx.lifecycle.ViewModel
import com.example.testquest.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    fun isTokenExist(): Boolean {
       return repository.isTokenExist()
    }
}