package com.example.input_button

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val city = findViewById<EditText>(R.id.EditText)
        val submitButton = findViewById<Button>(R.id.Click)
        submitButton.setOnClickListener{

            startActivity(Intent(this,Activity_2::class.java)
                .putExtra("city",city.text.toString()))
        }
    }


}