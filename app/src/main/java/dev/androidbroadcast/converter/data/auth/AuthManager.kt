package dev.androidbroadcast.converter.data.auth

import android.content.Context
import android.content.SharedPreferences

class AuthManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit()
            .putString("access_token", token)
            .putLong("token_time", System.currentTimeMillis())
            .apply()
    }

    fun getToken(): String? = prefs.getString("access_token", null)

    fun isTokenValid(): Boolean {
        val savedTime = prefs.getLong("token_time", 0L)
        val currentTime = System.currentTimeMillis()
        return getToken() != null && (currentTime - savedTime) < TOKEN_VALIDITY_MS
    }

    fun clearToken() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val TOKEN_VALIDITY_MS = 10 * 60 * 1000 // 10 minutes
    }
}
