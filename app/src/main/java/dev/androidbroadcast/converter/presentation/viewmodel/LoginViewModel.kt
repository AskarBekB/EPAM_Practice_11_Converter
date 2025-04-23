package dev.androidbroadcast.converter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dev.androidbroadcast.converter.data.auth.AuthManager

class LoginViewModel(private val authManager: AuthManager) : ViewModel() {

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        if (username == "user" && password == "pass") {
            val token = "token_${System.currentTimeMillis()}"
            authManager.saveToken(token)
            onResult(true)
        } else {
            onResult(false)
        }
    }

    fun isTokenValid(): Boolean = authManager.isTokenValid()
}
