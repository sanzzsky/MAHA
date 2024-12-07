package com.example.mahaapp

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val tabEmail = findViewById<TextView>(R.id.tab_email)
        val tabPhone = findViewById<TextView>(R.id.tab_phone)
        val iconInput = findViewById<ImageView>(R.id.icon_input)
        val etInput = findViewById<EditText>(R.id.et_input)

        tabEmail.setOnClickListener {
            // Ubah tampilan tab
            tabEmail.setBackgroundResource(R.drawable.tab_background_selected)
            tabPhone.setBackgroundResource(R.drawable.tab_background_unselected)

            // Ubah ikon dan input type untuk email
            iconInput.setImageResource(R.drawable.ic_email)
            etInput.hint = "Enter your email"
            etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        tabPhone.setOnClickListener {
            // Ubah tampilan tab
            tabPhone.setBackgroundResource(R.drawable.tab_background_selected)
            tabEmail.setBackgroundResource(R.drawable.tab_background_unselected)

            // Ubah ikon dan input type untuk nomor telepon
            iconInput.setImageResource(R.drawable.ic_phone)
            etInput.hint = "Enter your phone number"
            etInput.inputType = InputType.TYPE_CLASS_PHONE
        }
    }
}
