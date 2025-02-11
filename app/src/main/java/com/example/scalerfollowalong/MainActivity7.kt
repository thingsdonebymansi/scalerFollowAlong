package com.example.scalerfollowalong

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.scalerfollowalong.databinding.ActivityMain7Binding

class MainActivity7 : AppCompatActivity() {

    private lateinit var binding: ActivityMain7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain7Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        //setContentView(R.layout.activity_main7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main7)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var count = 0
        binding.btnAct7.setOnClickListener {
            count++
            binding.tVAct7.text = "Let us count numbers: $count"
            Toast.makeText(this, "Count button clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmitMyFoodPreferences.setOnClickListener{
            val selectedFoodType = binding.rGFoodType.checkedRadioButtonId
            val preferededFoodType = findViewById<RadioButton>(selectedFoodType)
            val selectedMainDishes = binding.cBMainDishes.isChecked
            val selectedSideDishes = binding.cBSideDishes.isChecked
            val selectedDesserts = binding.cBDesserts.isChecked
            val selectedDrinks = binding.cBDrinks.isChecked

            val preferences = "Your food preferences are:\n" + "${preferededFoodType.text}" +
                    (if(selectedMainDishes) "\nMain Dishes" else "") +
                    (if(selectedSideDishes) "\nSide Dishes" else "") +
                    (if(selectedDesserts) "\nDesserts" else "") +
                    (if(selectedDrinks) "\nDrinks" else "")

            binding.tVMyFoodPreferences.text = preferences

        }



    }
}