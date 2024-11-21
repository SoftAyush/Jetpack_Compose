package com.softdevayush.self_improvement.Views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softdevayush.self_improvement.Controller.LoginController
import com.softdevayush.self_improvement.R

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var loginResult by remember { mutableStateOf("") }
    val loginController = LoginController(context = LocalContext.current)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.white_man else R.drawable.black_man),
            contentDescription = "WellCome Image"
        )

        Text(
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = "Login to your Account", style = MaterialTheme.typography.bodyMedium)
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = when (isPasswordVisible) {
                true -> VisualTransformation.None
                false -> PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.show else R.drawable.hide),
                        contentDescription = "Toggle password visibility "
                    )

                }
            }

        )
        Spacer(modifier = Modifier.height(16.dp))

        if (loginResult.isNotEmpty()) {
            Text(
                text = loginResult,
                color = if (loginResult == "Login Successful") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                loginController.onLogin(username, password) { success ->
                    if (success) {
                        loginResult = "Login Successful"
                        onLoginSuccess()
                    } else {
                        loginResult = "Invalid Credentials"
                    }
                }
            }, modifier = Modifier
                .height(40.dp)
                .width(100.dp)
        ) {
            Text(text = "Login", fontWeight = FontWeight.W500)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onLoginSuccess = {})
}