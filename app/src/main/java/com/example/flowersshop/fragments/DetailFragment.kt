package com.example.flowersshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowersshop.R
import com.example.flowersshop.adapter.CircleRecyclerViewAdapter
import com.example.flowersshop.databinding.FragmentDetailBinding
import com.example.flowersshop.model.CircleData
import com.example.flowersshop.model.FlowersData
import com.squareup.picasso.Picasso
import kotlin.math.log


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val circleAdapter = CircleRecyclerViewAdapter()
    private lateinit var flowersData: FlowersData
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args:DetailFragmentArgs by navArgs()
        flowersData = args.item
        Log.d("tag","${args.item}")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCircle()
        changeQuantity()
        goBack()
        getFlowers()
    }

    private fun addCircle(){
       binding.circleRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.circleRv.adapter = circleAdapter
        val colorList = listOf(
            CircleData(R.drawable.circle),
            CircleData(R.drawable.circle_gray),
            CircleData(R.drawable.circle_dark),
            CircleData(R.drawable.circle_light_dark),
            CircleData(R.drawable.circle_purpple),
            CircleData(R.drawable.circle_blue)
        )
        circleAdapter.updateData(colorList)
    }
    private fun changeQuantity(){
         binding.minusNumberBtn.setOnClickListener {
             if (number > 0) {
                 number -= 1
                 binding.quantityNumberTv.text = number.toString()
             }

         }
        binding.plusNumberBtn.setOnClickListener {
            if (number < 10){
                number += 1
                binding.quantityNumberTv.text = number.toString()
            }
        }
    }

    private fun goBack(){
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
    private fun getFlowers() = with(binding){

        Picasso.get()
            .load(flowersData.image)
            .placeholder(R.drawable.ic_flower)
            .into(itemImage)
        titleFlower.text = flowersData.title
        sellsFlower.text = flowersData.sells
        scoreTv.text = flowersData.score
        priceTv.text = flowersData.price
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}