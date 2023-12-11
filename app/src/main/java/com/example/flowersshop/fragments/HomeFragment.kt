package com.example.flowersshop.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowersshop.adapter.RecyclerViewAdapter
import com.example.flowersshop.databinding.FragmentHomeBinding
import com.example.flowersshop.model.FlowersData
import java.util.UUID
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val allFlowers = listOf(
        FlowersData(UUID.randomUUID(), "https://www.shutterstock.com/shutterstock/photos/2242095875/display_1500/stock-photo-watercolor-illustration-of-a-sakura-branch-in-a-transparent-vase-isolated-on-a-white-background-2242095875.jpg","first flower","${Random.nextInt(1, 5)}","${Random.nextInt(100, 10000)}","$${Random.nextInt(50, 250)}"),
        FlowersData(UUID.randomUUID(), "https://www.shutterstock.com/shutterstock/photos/2240890637/display_1500/stock-photo-watercolor-illustration-of-a-sakura-branch-in-a-transparent-vase-isolated-on-a-white-background-2240890637.jpg","second flower","${Random.nextInt(1, 5)}","${Random.nextInt(100, 10000)}","$${Random.nextInt(50, 250)}"),
        FlowersData(UUID.randomUUID(), "https://www.shutterstock.com/shutterstock/photos/2156621139/display_1500/stock-photo--d-illustration-flowers-vase-decoration-isolated-on-white-background-2156621139.jpg","third flower","${Random.nextInt(1, 5)}","${Random.nextInt(100, 10000)}","$${Random.nextInt(50, 250)}"),
        FlowersData(UUID.randomUUID(), "https://www.shutterstock.com/shutterstock/photos/2242095875/display_1500/stock-photo-watercolor-illustration-of-a-sakura-branch-in-a-transparent-vase-isolated-on-a-white-background-2242095875.jpg","fourth flower","${Random.nextInt(1, 5)}","${Random.nextInt(100, 10000)}","$${Random.nextInt(50, 250)}"),
        FlowersData(UUID.randomUUID(), "https://www.shutterstock.com/shutterstock/photos/2242095875/display_1500/stock-photo-watercolor-illustration-of-a-sakura-branch-in-a-transparent-vase-isolated-on-a-white-background-2242095875.jpg","fifth flower","${Random.nextInt(1, 5)}","${Random.nextInt(100, 10000)}","$${Random.nextInt(50, 250)}"),
        FlowersData(UUID.randomUUID(), "https://www.shutterstock.com/shutterstock/photos/2242095875/display_1500/stock-photo-watercolor-illustration-of-a-sakura-branch-in-a-transparent-vase-isolated-on-a-white-background-2242095875.jpg","sixth flower","${Random.nextInt(1, 5)}","${Random.nextInt(100, 10000)}","$${Random.nextInt(50, 250)}")
    )

    private val itemList: MutableList<FlowersData> = mutableListOf()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeFlowers()
    }

    private fun makeFlowers() {
        adapter = RecyclerViewAdapter()
        binding.flowersRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.flowersRv.adapter = adapter
        adapter.setOnItemClickListener {item ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    item
                )
            )
        }
        adapter.setData(itemList)

        itemList.addAll(
            allFlowers
        )
        binding.searchEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterItems(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                //Not used
            }

        }
        )
    }
    private fun filterItems(query: String){
        val filteredList = allFlowers.filter { it.title.contains(query, ignoreCase = true) }
        adapter.setData(filteredList)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}