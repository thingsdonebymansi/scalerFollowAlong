/*
package com.example.scalerfollowalong

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.scalerfollowalong.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    // Declaring the binding object
    private lateinit var binding: ActivityMain2Binding
    private lateinit var imageView: ImageView
    private lateinit var selectImageButton: Button
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private var REQUEST_CODE_PERMISSIONS = 101

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


        imageView = findViewById(R.id.ivImage)
        selectImageButton = findViewById(R.id.btnAddImage)

        // Register the ActivityResultLauncher
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                if(data != null){
                    val selectedImageUri: Uri? = data.data
                    if(selectedImageUri != null){
                        imageView.setImageURI(selectedImageUri)
                    }
                }
            }
        }

        selectImageButton.setOnClickListener{
            if (checkPermissions()){
                openImagePicker()
            } else {
                requestPermissions()

            }
        }


    }

    private fun openImagePicker(){
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        pickImageLauncher.launch(intent)

    }

    // On Android 13 (Tiramisu) and above, we need READ_MEDIA_IMAGES.
    // On older versions, we need READ_EXTERNAL_STORAGE.

    private fun checkPermissions(): Boolean{
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE

        }
        return ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions(){
        val permission = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        ActivityCompat.requestPermissions(this,permission,REQUEST_CODE_PERMISSIONS)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker()
            } else {
                // Determine which permission was denied (if multiple were requested)
                val deniedPermission = if (permissions.isNotEmpty()) permissions[0] else "unknown permission"
                Toast.makeText(this, "Permission denied for $deniedPermission", Toast.LENGTH_SHORT).show()
            }
        }
    }


}*/

 */



 package com.example.scalerfollowalong

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.scalerfollowalong.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private var selectedImageUri: Uri? = null
    private val REQUEST_CODE_PERMISSIONS = 100

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("MainActivity2", "Selected URI: $uri")
            selectedImageUri = uri
            binding.ivImage.setImageURI(uri)
        } else {
            Log.d("MainActivity2", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main2)

        binding.btnAct2.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.email.text.toString()
            val password = binding.etPassword.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("MainActivity2", "First Name: $firstName, Last Name: $lastName, Email: $email")
        }

        binding.btnAddImage.setOnClickListener {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        Log.d("MainActivity2", "openImagePicker() called")
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }
}
