package com.example.scalerfollowalong

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.scalerfollowalong.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    // Declaring the binding object
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Inflate the layout using the binding object
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root) // Use the root view of the binding object

        enableEdgeToEdge()
        //setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main7)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAct2.setOnClickListener{

            //Toast.makeText(this, "Sign up button clicked", Toast.LENGTH_SHORT).show()

            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.email.text.toString()
            val password = binding.etPassword.text.toString()

            // Basic input validation
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Basic email validation (you can use a more robust regex)
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // In a real app, don't log the password!
            Log.d("MainActivity2", "First Name: $firstName, Last Name: $lastName, Email: $email")
            Toast.makeText(this, "Sign up button clicked", Toast.LENGTH_SHORT).show()

            //Log.d("MainActivity2", "First Name: $firstName, Last Name: $lastName, Email: $email, Password: $password")

        }

        binding.btnAddImage.setOnClickListener{
            binding.ivImage.setImageResource(R.drawable.howl)
        }


    }
}