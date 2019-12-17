package com.example.recyclerview

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.powietrze_gios.R
import com.example.powietrze_gios.activity.MainActivity
import com.example.powietrze_gios.activity.SensorActivity
import com.example.powietrze_gios.model.Model
import kotlinx.android.synthetic.main.list_view.view.*
import java.security.AccessController.getContext


class MainAdapter(val airs: List<Model.Result>, val listener: (Model.Result) -> Unit) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(station: Model.Result, listener: (Model.Result) -> Unit) = with(itemView){
            tv_title.text = station.name
            tv_points.text = "99"
        }

    }
    override fun getItemCount(): Int {
        return airs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_view, parent, false)
        return MainViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(airs.get(position), listener)
        holder.itemView.setOnClickListener{
            Log.i("sasa", "MainAdaper: ${airs.get(position)}")
            val intent = Intent(holder.itemView.context, SensorActivity::class.java)
            intent.putExtra("MAIN_ID", airs.get(position).id)
            holder.itemView.context.startActivity(intent)

        }
    }

}
