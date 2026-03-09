package com.example.bookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val tvName = findViewById<TextView>(R.id.tvSummaryName)
        val tvPhone = findViewById<TextView>(R.id.tvSummaryPhone)
        val tvEmail = findViewById<TextView>(R.id.tvSummaryEmail)
        val tvType = findViewById<TextView>(R.id.tvSummaryType)
        val tvDate = findViewById<TextView>(R.id.tvSummaryDate)
        val tvTime = findViewById<TextView>(R.id.tvSummaryTime)
        val tvGender = findViewById<TextView>(R.id.tvSummaryGender)
        val btnBackHome = findViewById<Button>(R.id.btnBackHome)

        val name = intent.getStringExtra("NAME")
        val phone = intent.getStringExtra("PHONE")
        val email = intent.getStringExtra("EMAIL")
        val type = intent.getStringExtra("TYPE")
        val date = intent.getStringExtra("DATE")
        val time = intent.getStringExtra("TIME")
        val gender = intent.getStringExtra("GENDER")

        tvName.text = "Name: $name"
        tvPhone.text = "Phone: $phone"
        tvEmail.text = "Email: $email"
        tvType.text = "Appointment Type: $type"
        tvDate.text = "Date: $date"
        tvTime.text = "Time: $time"
        tvGender.text = "Gender: $gender"

        btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}