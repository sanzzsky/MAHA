package com.example.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mahaapp.databinding.ActivityLoginBinding
import com.example.ui.signup.SignupActivity
import com.example.mahaapp.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani tombol login
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            handleLogin(email, password)
        }

        // Menangani tombol signup
        binding.tvNewSignup.setOnClickListener {
            // Berpindah ke halaman Sign Up
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    // Fungsi untuk menangani login
    private fun handleLogin(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else if (isValidEmail(email) && isValidPassword(password)) {
            // Menyimpan status login ke SharedPreferences
            val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

            // Menampilkan pesan sukses login
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            // Arahkan pengguna ke MainActivity setelah login berhasil
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Menutup LoginActivity
        } else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
        }
    }

    // Fungsi validasi email
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Fungsi validasi password
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}
