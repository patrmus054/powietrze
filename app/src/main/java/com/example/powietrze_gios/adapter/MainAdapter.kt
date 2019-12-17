package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powietrze_gios.R
import com.example.powietrze_gios.model.Model
import kotlinx.android.synthetic.main.list_view.view.*
import java.util.ArrayList

class MainAdapter(val airs: List<Model.StationName>, val listener: (Model.StationName) -> Unit) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(station: Model.StationName, listener: (Model.StationName) -> Unit) = with(itemView){
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
//        val airTitle = airs.get(position).name
//        holder.view.tv_count.text = "$position "
//        holder.view.tv_title.text = "$airTitle "
//        holder.view.tv_points.text = "100"

//        holder.itemView.setOnClickListener{
//            val item = taskList[position]
//            val intent = Intent(holder.itemView.context, UpdateTaskActivity::class.java)
//            intent.putExtra(MainActivity.TASK_ID, item.id)
//            holder.itemView.context.startActivity(intent)
//        }
    }

}
