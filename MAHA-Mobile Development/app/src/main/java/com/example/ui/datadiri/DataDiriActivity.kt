package com.example.ui.datadiri

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.mahaapp.R
import com.example.ui.login.LoginActivity

class DataDiriActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtHealthCondition: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_diri)

        // Initialize views
        edtName = findViewById(R.id.edt_name)
        edtAge = findViewById(R.id.edt_age)
        edtWeight = findViewById(R.id.edt_weight)
        edtHeight = findViewById(R.id.edt_height)
        edtHealthCondition = findViewById(R.id.edt_health_condition)
        rgGender = findViewById(R.id.rg_gender)
        rbMale = findViewById(R.id.rb_male)
        rbFemale = findViewById(R.id.rb_female)
        btnSubmit = findViewById(R.id.btn_submit)

        // Handle button click
        btnSubmit.setOnClickListener {
            submitData()
        }
    }

    private fun submitData() {
        val name = edtName.text.toString().trim()
        val age = edtAge.text.toString().trim()
        val weight = edtWeight.text.toString().trim()
        val height = edtHeight.text.toString().trim()
        val healthCondition = edtHealthCondition.text.toString().trim()

        // Check for gender selection
        val gender = when {
            rbMale.isChecked -> "Male"
            rbFemale.isChecked -> "Female"
            else -> "Not selected"
        }

        // Validate inputs
        if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || healthCondition.isEmpty() || gender == "Not selected") {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Here you can send the data to a server, save it locally, or whatever you need to do with the data
        val message = "Data submitted successfully:\nName: $name\nAge: $age\nGender: $gender\nWeight: $weight\nHeight: $height\nHealth Condition: $healthCondition"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // Arahkan pengguna ke halaman Login setelah submit data
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()  // Menutup DataDiriActivity

    }
}
