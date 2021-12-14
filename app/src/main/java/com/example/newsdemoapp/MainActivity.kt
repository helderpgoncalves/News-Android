package com.example.newsdemoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_row.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchNews()
    }

    private fun fetchNews() {
        val url = "https://editionyou.com/cache/data-guardian.json"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", "Failed to execute Request")
                Log.e("Error", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val newsFeed = gson.fromJson(body, Api::class.java)
                val adapter = MainAdapter(newsFeed, this@MainActivity)

                runOnUiThread {
                    recyclerView_main.adapter = adapter
                }
            }
        })
    }
}

class Api(val list: List<News>)

class News(
    val id: Int,
    val url: String,
    val image: String,
    val title: String,
    val time: String,
    val published: String,
    val mediasource: String,
)
