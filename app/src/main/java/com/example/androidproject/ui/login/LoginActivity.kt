package com.example.androidproject.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.MainActivity
import com.example.androidproject.databinding.ActivityLoginBinding

import android.content.Context
import android.content.Intent

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (getLoginStatus()) {
            navigateToHome()
            finish()
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            saveLoginStatus(true,username)
            navigateToHome()
            finish()
        }
    }

    private fun authenticateUser(username: String, password: String): Boolean {
        return username == "admin" && password == "admin"
    }

    private fun saveLoginStatus(isLoggedIn: Boolean, username: String) {
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
        sharedPreferences.edit().putString("userName", username).apply()
    }

    private fun getLoginStatus(): Boolean {
        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
