package com.example.scalerfollowalong

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main6)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backbutton06 = findViewById<ImageButton>(R.id.ibtnBack06)
        val nextbutton06 = findViewById<ImageButton>(R.id.ibtnNext06)

        backbutton06.setOnClickListener {
            finish()
        }

        nextbutton06.setOnClickListener {
            Intent(this, MainActivity7::class.java).also {
                startActivity(it)
            }
        }
    }
}