package com.example.ui.datadiri

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Spinner
import com.example.mahaapp.R
import com.example.ui.login.LoginActivity

class DataDiriActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var spHealthCondition: Spinner
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
        spHealthCondition = findViewById(R.id.sp_health_condition)
        rgGender = findViewById(R.id.rg_gender)
        rbMale = findViewById(R.id.rb_male)
        rbFemale = findViewById(R.id.rb_female)
        btnSubmit = findViewById(R.id.btn_submit)

        // Handle button click
        btnSubmit.setOnClickListener {
            submitData()
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.health_conditions, // Pastikan array ini ada di res/values/strings.xml
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spHealthCondition.adapter = adapter


    }

    private fun submitData() {
        val name = edtName.text.toString().trim()
        val age = edtAge.text.toString().trim()
        val weight = edtWeight.text.toString().trim()
        val height = edtHeight.text.toString().trim()
        val healthCondition = spHealthCondition.selectedItem.toString()


        val gender = when {
            rbMale.isChecked -> "Male"
            rbFemale.isChecked -> "Female"
            else -> "Not selected"
        }

        if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || healthCondition.isEmpty() || gender == "Not selected") {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }


        if (healthCondition == "Pilih Kondisi Kesehatan") {
            Toast.makeText(this, "Please select a health condition", Toast.LENGTH_SHORT).show()
            return
        }

        val message = "Data submitted successfully:\nName: $name\nAge: $age\nGender: $gender\nWeight: $weight\nHeight: $height\nHealth Condition: $healthCondition"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

    }
}
