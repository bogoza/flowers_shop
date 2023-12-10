package com.example.flowersshop.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersshop.R
import com.example.flowersshop.databinding.HomeRvBinding
import com.example.flowersshop.fragments.HomeFragment
import com.example.flowersshop.fragments.HomeFragmentDirections
import com.example.flowersshop.model.FlowersData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerViewAdapter:ListAdapter<FlowersData,RecyclerViewAdapter.MyViewHolder>(object :
     DiffUtil.ItemCallback<FlowersData>(){
    override fun areItemsTheSame(oldItem: FlowersData, newItem: FlowersData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FlowersData, newItem: FlowersData): Boolean {
        return oldItem == newItem
    }

}) {
    fun setData(item:List<FlowersData>){
        submitList(item)
        notifyDataSetChanged()
    }
   inner class MyViewHolder(private val binding:HomeRvBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(flower:FlowersData){
            val navController: NavController? = null
            val item = currentList[adapterPosition]
            Picasso.get()
                .load(flower.image)
                .placeholder(R.drawable.ic_flower)
                .into(binding.flowerImageview,object :Callback{
                        override fun onSuccess() {
                    // Image loaded successfully
                    Log.d("Picasso", "Image loaded successfully")
                }

                        override fun onError(e: Exception?) {
                    // Log the error
                    Log.e("Picasso", "Error loading image", e)
                }
        })
            binding.titleTv.text = item.title
            binding.scoreTv.text = item.score
            binding.sellsTextview.text = item.sells
            binding.priceTv.text = item.price

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            HomeRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener(

            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment)
        )
    }

}