package com.example.flowersshop.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersshop.R
import com.example.flowersshop.databinding.CircleRvBinding
import com.example.flowersshop.model.CircleData

class CircleRecyclerViewAdapter: RecyclerView.Adapter<CircleRecyclerViewAdapter.CircleViewHolder>() {
    private var circleList = emptyList<CircleData>()
    private var selectedPosition = RecyclerView.NO_POSITION
    inner class CircleViewHolder(private val binding:CircleRvBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(color:CircleData,onItemClick:(Int)->(Unit)){
            binding.circleIv.setBackgroundResource(color.circleColor)

            binding.circleIv.setOnClickListener {
                onItemClick(adapterPosition)
            }
            binding.selectedCircleBtn.visibility = if (adapterPosition == selectedPosition) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CircleRvBinding.inflate(inflater,parent,false)
        return CircleViewHolder(binding)
    }

    override fun getItemCount(): Int = circleList.size

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val currentColor = circleList[position]
        holder.bind(currentColor) {onCircleItemClick(it)}
    }
    fun updateData(newList: List<CircleData>) {
        circleList = newList
        notifyDataSetChanged()
    }
    private fun onCircleItemClick(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

}