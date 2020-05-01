package com.example.happyhour.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.happyhour.R
import com.example.happyhour.data.cocktail.DrinksDetails
import com.example.happyhour.ui.SharedSearchViewModel

/**
 * A simple [Fragment] subclass.
 */
class SearchResultsFragment : Fragment(),
    SearchRecyclerAdapter.DrinkItemListener {
    private lateinit var searchViewModel: SharedSearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)


        val root = inflater.inflate(R.layout.fragment_search_results, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)

        searchViewModel.drinksData.observe(viewLifecycleOwner, Observer {
            val adapter = SearchRecyclerAdapter(
                requireContext(),
                it,
                this
            )
            recyclerView.adapter = adapter
        })
        // Inflate the layout for this fragment
        return root
    }
    override fun onDrinkItemClick(drink: DrinksDetails) {
        searchViewModel.selectedDrink.value = drink
        navController.navigate(R.id.action_searchResultsFragment_to_detailFragment2)
    }

}
