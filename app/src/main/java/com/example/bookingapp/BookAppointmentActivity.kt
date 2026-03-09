package com.example.bookingapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var spinnerType: Spinner
    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView
    private lateinit var rgGender: RadioGroup
    private lateinit var cbTerms: CheckBox
    private lateinit var btnConfirm: Button

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        etFullName = findViewById(R.id.etFullName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        spinnerType = findViewById(R.id.spinnerType)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        rgGender = findViewById(R.id.rgGender)
        cbTerms = findViewById(R.id.cbTerms)
        btnConfirm = findViewById(R.id.btnConfirm)

        // Setup Spinner
        val options = arrayOf("Doctor Consultation", "Dentist Appointment", "Eye Specialist", "Skin Specialist", "General Checkup")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter = adapter

        // Date Picker
        findViewById<Button>(R.id.btnSelectDate).setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, y, m, d ->
                selectedDate = "$d/${m + 1}/$y"
                tvSelectedDate.text = "Selected Date: $selectedDate"
            }, year, month, day)
            dpd.show()
        }

        // Time Picker
        findViewById<Button>(R.id.btnSelectTime).setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this, { _, h, min ->
                selectedTime = String.format("%02d:%02d", h, min)
                tvSelectedTime.text = "Selected Time: $selectedTime"
            }, hour, minute, true)
            tpd.show()
        }

        btnConfirm.setOnClickListener {
            if (validateInputs()) {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("NAME", etFullName.text.toString())
                intent.putExtra("PHONE", etPhone.text.toString())
                intent.putExtra("EMAIL", etEmail.text.toString())
                intent.putExtra("TYPE", spinnerType.selectedItem.toString())
                intent.putExtra("DATE", selectedDate)
                intent.putExtra("TIME", selectedTime)
                
                val selectedGenderId = rgGender.checkedRadioButtonId
                val gender = if (selectedGenderId != -1) {
                    findViewById<RadioButton>(selectedGenderId).text.toString()
                } else {
                    "Not Specified"
                }
                intent.putExtra("GENDER", gender)
                
                startActivity(intent)
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (etFullName.text.toString().isEmpty()) {
            etFullName.error = "Name is required"
            return false
        }
        if (etPhone.text.toString().isEmpty()) {
            etPhone.error = "Phone is required"
            return false
        }
        if (etEmail.text.toString().isEmpty()) {
            etEmail.error = "Email is required"
            return false
        }
        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            return false
        }
        if (selectedTime.isEmpty()) {
            Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!cbTerms.isChecked) {
            Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}