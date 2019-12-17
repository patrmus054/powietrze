package com.example.powietrze_gios.network

import com.example.powietrze_gios.model.Model
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("station/findAll")
    fun fetchAllStations(): Call<List<Model.Result>>

//    @GET("station/sensors/${stationId}")
//    fun getStationID(var stationId: Int): Call<List<Model.id>>
    @GET("station/sensors/{id}")
    fun fetchAllSensors(@Path("id") id: Int): Call<List<Model.Sensors>>

}