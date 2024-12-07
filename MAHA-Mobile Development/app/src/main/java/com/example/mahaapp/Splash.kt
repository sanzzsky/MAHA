package com.example.mahaapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.ui.login.LoginActivity

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {

    companion object {
        private const val PREFS_NAME = "UserPrefs"
        private const val IS_LOGGED_IN = "isLoggedIn"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val sharedPreferences: SharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

            val nextIntent = if (isLoggedIn) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(nextIntent)
            finish()
        }, 800)
    }
}
