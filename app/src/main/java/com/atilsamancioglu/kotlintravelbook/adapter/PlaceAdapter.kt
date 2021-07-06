package com.atilsamancioglu.kotlintravelbook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.kotlintravelbook.databinding.RecyclerRowBinding
import com.atilsamancioglu.kotlintravelbook.model.Place
import com.atilsamancioglu.kotlintravelbook.view.MapsActivity

class PlaceAdapter(var placeList: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val recyclerRowBinding: RecyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.recyclerRowBinding.recyclerViewTextView.setText(placeList[position].name)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            intent.putExtra("place", placeList[position])
            intent.putExtra("info", "old")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    class PlaceHolder(val recyclerRowBinding: RecyclerRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }
}
