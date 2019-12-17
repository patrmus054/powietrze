package com.example.powietrze_gios.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powietrze_gios.R
import com.example.powietrze_gios.adapter.SensorAdapter
import com.example.powietrze_gios.model.Model
import com.example.powietrze_gios.network.ApiService
import com.example.recyclerview.MainAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SensorActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: SensorAdapter

    var sensors: List<Model.Sensors> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val retrofit = Retrofit.Builder().baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(ApiService::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = SensorAdapter(sensors){
            Toast.makeText(this, "${it.id} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerView = findViewById<RecyclerView>(R.id.recycle_sensors) as RecyclerView
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        val sensorId = intent.getIntExtra("MAIN_ID", -1)


        api.fetchAllSensors(sensorId).enqueue(object : Callback<List<Model.Sensors>> {
            override fun onFailure(call: Call<List<Model.Sensors>>, t: Throwable) {
                Log.e("dupa", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<List<Model.Sensors>>, response: Response<List<Model.Sensors>>) {
                Log.i("dupa", "onResponse: ${response.body()!![0].id}")
                sensors = response.body()!!
                viewAdapter = SensorAdapter(sensors){
                }
                recyclerView.adapter = viewAdapter
            }

        })
    }
}
