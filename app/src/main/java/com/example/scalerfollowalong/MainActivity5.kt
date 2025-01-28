package com.example.scalerfollowalong

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity5 : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdaptor: MyAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main5)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, response: Response<MyData?>) {
                val datalist = response.body()?.data!!
                //val textView = findViewById<TextView>(R.id.apitextview)
                //textView.text = datalist.toString()

                myAdaptor = MyAdaptor(this@MainActivity5, datalist)
                myRecyclerView.adapter = myAdaptor
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity5)

                Log.d("TAG: onResponse", "onResponse: $datalist")
            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                Log.d("TAG: onFailure", "onFailure: " + p1.message)
            }
        })

    }
}