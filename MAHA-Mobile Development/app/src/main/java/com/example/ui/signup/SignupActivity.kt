package com.example.ui.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mahaapp.databinding.ActivitySignupBinding
import com.example.ui.datadiri.DataDiriActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan View Binding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani tombol signup
        binding.btnSignup.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()
            handleSignup(email, password, confirmPassword)
        }

        // Menangani tombol untuk kembali ke halaman login
        binding.tvHaveAccount.setOnClickListener {
            // Berpindah ke halaman Login
            finish()  // Menutup SignUpActivity dan kembali ke LoginActivity
        }
    }

    // Fungsi untuk menangani proses signup
    private fun handleSignup(email: String, password: String, confirmPassword: String) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else if (isValidEmail(email) && isValidPassword(password)) {
            // Menyimpan data pengguna yang sudah terdaftar ke SharedPreferences
            val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isRegistered", true).apply()

            // Menampilkan pesan sukses signup
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

            // Arahkan pengguna ke halaman DataDiriActivity setelah signup
            val intent = Intent(this, DataDiriActivity::class.java)
            startActivity(intent)
            // Setelah pendaftaran berhasil, arahkan pengguna ke halaman login
            finish()  // Menutup SignUpActivity dan kembali ke LoginActivity
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
