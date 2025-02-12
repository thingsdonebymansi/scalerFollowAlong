
package com.example.scalerfollowalong

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.scalerfollowalong.R.layout.custom_toast
import com.example.scalerfollowalong.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnNormalToast.setOnClickListener {
            Toast.makeText(this, "This is a normal toast", Toast.LENGTH_LONG).show()

        }

        binding.btnCustomToast.setOnClickListener{
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                view = layoutInflater.inflate(custom_toast, findViewById(R.id.cLCustomToast))
                show()
            }
        }



    }
}