package com.example.ui.datadiri

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RadioButton
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mahaapp.R
import com.example.ui.login.LoginActivity

class DataDiriActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var rgDiabetes: RadioGroup
    private lateinit var rgHypertension: RadioGroup
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_diri)

        // Initialize views
        edtName = findViewById(R.id.edt_name)
        edtAge = findViewById(R.id.edt_age)
        edtWeight = findViewById(R.id.edt_weight)
        edtHeight = findViewById(R.id.edt_height)
        rgGender = findViewById(R.id.rg_gender)
        rbMale = findViewById(R.id.rb_male)
        rbFemale = findViewById(R.id.rb_female)
        rgDiabetes = findViewById(R.id.rg_diabetes)
        rgHypertension = findViewById(R.id.rg_hypertension)
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

        // Get selected options for Diabetes and Hypertension
        val diabetes = when (rgDiabetes.checkedRadioButtonId) {
            R.id.rb_diabetes_yes -> "Ya"
            R.id.rb_diabetes_no -> "Tidak"
            else -> "Not selected"
        }

        val hypertension = when (rgHypertension.checkedRadioButtonId) {
            R.id.rb_hypertension_yes -> "Ya"
            R.id.rb_hypertension_no -> "Tidak"
            else -> "Not selected"
        }

        // Get selected gender
        val gender = when {
            rbMale.isChecked -> "Male"
            rbFemale.isChecked -> "Female"
            else -> "Not selected"
        }

        // Validate inputs
        if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() ||
            diabetes == "Not selected" || hypertension == "Not selected" || gender == "Not selected") {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Display success message
        val message = """
            Data submitted successfully:
            Name: $name
            Age: $age
            Gender: $gender
            Weight: $weight
            Height: $height
            Diabetes: $diabetes
            Hypertension: $hypertension
        """.trimIndent()

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // Save the name to SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userName", name)
        editor.apply()  // Apply changes asynchronously

        // Navigate to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
