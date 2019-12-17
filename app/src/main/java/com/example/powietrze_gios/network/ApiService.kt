package com.example.powietrze_gios.network

import com.example.powietrze_gios.model.Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

interface ApiService {
//   @GET("station/findAll")
//    fun hitCountCheck(@Query("stationName") name: String): Call<ArrayList<Model.StationName>>
//
//    companion object {
//        fun create(): ApiService {
//
//            val retrofit = Retrofit.Builder()
//                .addCallAdapterFactory(
//                    RxJava2CallAdapterFactory.create())
//                .addConverterFactory(
//                    GsonConverterFactory.create())
//                .baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
//                .build()
//
//            return retrofit.create(ApiService::class.java)
//        }
//    }

    @GET("station/findAll")
    fun fetchAllUsers(): Call<List<Model.StationName>>
}