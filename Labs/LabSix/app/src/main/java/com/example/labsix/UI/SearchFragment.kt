package com.example.labsix.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.labsix.R
import com.example.labsix.data.DrinksDetails

class SearchFragment : Fragment(), SearchRecyclerAdapter.DrinkItemListener {

    private lateinit var searchViewModel: SharedSearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)

        searchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        searchViewModel.drinksData.observe(viewLifecycleOwner, Observer {
            val adapter = SearchRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
        })
        return root
    }

    override fun onDrinkItemClick(drink: DrinksDetails) {
        searchViewModel.selectedDrink.value = drink
        navController.navigate(R.id.action_searchFragment_to_detailFragment)
    }
}
