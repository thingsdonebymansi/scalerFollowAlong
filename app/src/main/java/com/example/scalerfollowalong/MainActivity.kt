package com.example.scalerfollowalong

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonHot = findViewById<Button>(R.id.btnHot)
        val buttonCool = findViewById<Button>(R.id.btnCool)
        val linearLayout = findViewById<LinearLayout>(R.id.main)

        buttonCool.setOnClickListener {
            linearLayout.setBackgroundColor(getColor(R.color.teal))
            //Toast.makeText(this, "Cool", Toast.LENGTH_SHORT).show()
        }
        buttonHot.setOnClickListener {
            linearLayout.setBackgroundColor(getColor(R.color.red))
            //Toast.makeText(this, "Hot", Toast.LENGTH_SHORT).show()
        }

        val btnAddition = findViewById<Button>(R.id.btnAddition)
        var ptFirstNumber = findViewById<EditText>(R.id.ptFirstNumber)
        var ptSecondNumber = findViewById<EditText>(R.id.ptSecondNumber)
        var tVResult = findViewById<TextView>(R.id.tVResult)

        btnAddition.setOnClickListener {
            val FirstNumber = ptFirstNumber.text.toString().toInt()
            val SecondNumber = ptSecondNumber.text.toString().toInt()
            val result = FirstNumber + SecondNumber
            tVResult.text = result.toString()

        }
    }
}