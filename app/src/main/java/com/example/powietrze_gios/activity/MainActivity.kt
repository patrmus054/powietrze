package com.example.powietrze_gios.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powietrze_gios.R
import com.example.powietrze_gios.model.Model
import com.example.powietrze_gios.network.ApiService
import com.example.recyclerview.MainAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: MainAdapter

     var airs: List<Model.Result> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val retrofit = Retrofit.Builder().baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(ApiService::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MainAdapter(airs){
            Toast.makeText(this, "${it.name} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerView = findViewById<RecyclerView>(R.id.recycle) as RecyclerView
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        api.fetchAllStations().enqueue(object : Callback<List<Model.Result>>{
            override fun onFailure(call: Call<List<Model.Result>>, t: Throwable) {
                Log.e("dupa", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<List<Model.Result>>, response: Response<List<Model.Result>>) {
                Log.i("dupa", "onResponse: ${response.body()!![0].name}")
                airs = response.body()!!
                viewAdapter = MainAdapter(airs){
                }
                recyclerView.adapter = viewAdapter
            }

        })
    }
}
