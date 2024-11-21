package com.softdevayush.self_improvement.Controller

import android.content.Context
import com.softdevayush.self_improvement.Model.UserCredentials

class LoginController(private val context: Context) {
    private var userCredentials = UserCredentials(context = context)

    fun onLogin(username: String, password: String, onLoginResult: (Boolean) -> Unit) {
        userCredentials = UserCredentials(username, password,context)

        if (userCredentials.validate()) {
            userCredentials.setUserLoggedIn(true)
            onLoginResult(true)
        } else {
            userCredentials.setUserLoggedIn(false)
            onLoginResult(false)
        }
    }
}