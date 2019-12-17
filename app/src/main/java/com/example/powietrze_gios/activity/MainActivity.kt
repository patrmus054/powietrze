package com.example.powietrze_gios.activity

import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powietrze_gios.R
import com.example.powietrze_gios.model.Model
import com.example.powietrze_gios.network.ApiService
import com.example.recyclerview.MainAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//val ApiServe by lazy {
//    ApiService.create()
//}
//var disposable: Disposable? = null
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: MainAdapter

     var airs: List<Model.StationName> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        val retrofit = Retrofit.Builder().baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchAllUsers().enqueue(object : Callback<List<Model.StationName>>{
            override fun onFailure(call: Call<List<Model.StationName>>, t: Throwable) {
                Log.e("dupa", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<List<Model.StationName>>, response: Response<List<Model.StationName>>) {
                Log.i("dupa", "onResponse: ${response.body()!![0].name}")
                airs = response.body()!!
                Log.i("dupa2", "onResponse: ${airs.forEach{
                Log.i("dupa3", "onResponse: ${it.name}")}
                }")
            }

        })


        viewManager = LinearLayoutManager(this)
        viewAdapter = MainAdapter(airs){
            Toast.makeText(this, "${it.name} Clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerView = findViewById<RecyclerView>(R.id.recycle) as RecyclerView
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter
//        recycle.layoutManager = LinearLayoutManager(this)
//        recycle.adapter = MainAdapter(airs)
    }


//    private fun beginSearch(): LiveData<ArrayList<>> {
//        disposable =
//            ApiServe.hitCountCheck("query").enqueue(object : Callback<ArrayList<Model.StationName>>)
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(
////                    { result -> showResult(result.query.searchinfo.totalhits) },
////                    { error -> showError(error.message) }
////                )
//    }


}
