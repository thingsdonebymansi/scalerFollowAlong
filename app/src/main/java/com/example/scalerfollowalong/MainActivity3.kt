package com.example.scalerfollowalong

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    private var currentImage = 0
    private lateinit var image : ImageView
    private var pics = arrayOf("Picture 1", "Picture 2", "Picture 3", "Picture 4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nextButton = findViewById<ImageButton>(R.id.btnNext)
        val prevButton = findViewById<ImageButton>(R.id.btnPrev)
        val picName = findViewById<TextView>(R.id.imgName)

        nextButton.setOnClickListener {
            // i want to get the next image
            var idCurrentImage = "pic$currentImage"

            // i have to get the integer address associated with each view
            var idCurrentImageInt = this.resources.getIdentifier(idCurrentImage, "id", this.packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f

            currentImage = (4 + currentImage + 1) % 4
            var idNextImage = "pic$currentImage"
            var idNextImageInt = this.resources.getIdentifier(idNextImage, "id", this.packageName)
            image = findViewById(idNextImageInt)
            image.alpha = 1f

            picName.text = pics[currentImage]
        }

        prevButton.setOnClickListener {
            // i want to get the previous image
            var idCurrentImage = "pic$currentImage"

            // i have to get the integer address associated with each view
            var idCurrentImageInt = this.resources.getIdentifier(idCurrentImage, "id", this.packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f

            currentImage = (4 + currentImage - 1) % 4
            var idPrevImage = "pic$currentImage"
            var idPrevImageInt = this.resources.getIdentifier(idPrevImage, "id", this.packageName)
            image = findViewById(idPrevImageInt)
            image.alpha = 1f

            picName.text = pics[currentImage]
        }


    }
}