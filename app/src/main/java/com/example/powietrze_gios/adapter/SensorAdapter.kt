package com.example.powietrze_gios.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powietrze_gios.R
import com.example.powietrze_gios.model.Model
import kotlinx.android.synthetic.main.list_view_sensors.view.*

class SensorAdapter(val sensors: List<Model.Sensors>, val listener: (Model.Sensors) -> Unit) : RecyclerView.Adapter<SensorAdapter.SensorViewHolder>(){

    class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(sensor: Model.Sensors, listener: (Model.Sensors) -> Unit) = with(itemView){
            tv_id?.text = sensor.id.toString()
            tv_params?.text = sensor.param.toString()
        }

    }
    override fun getItemCount(): Int {
        return sensors.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_view_sensors, parent, false)
        return SensorViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        holder.bind(sensors.get(position), listener)
        holder.itemView.setOnClickListener{
            Log.i("sasa", "SensorAdaper: ${sensors.get(position)}")
        }
    }

}
