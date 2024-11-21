package com.softdevayush.self_improvement.Model

import android.content.Context
import android.content.SharedPreferences

data class UserCredentials(
    val username: String = "",
    val password: String = "",
    private val context: Context,
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // Dummy validation logic: Replace with actual authentication logic
    fun validate(): Boolean {
        return username == "user" && password == "user"
    }

    // Store login status in SharedPreferences
    fun setUserLoggedIn(loggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", loggedIn).apply()
    }

    // Check if the user is already logged in
    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}