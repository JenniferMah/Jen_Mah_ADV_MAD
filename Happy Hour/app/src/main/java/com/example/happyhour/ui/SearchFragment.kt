package com.example.happyhour.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.happyhour.R

class SearchFragment : Fragment(){

    private lateinit var searchViewModel: SharedSearchViewModel
    private lateinit var navController: NavController

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_search, container, false)

        searchButton = root.findViewById(R.id.searchButton)
        searchEditText = root.findViewById(R.id.searchInput)

        searchButton.setOnClickListener {
            searchDrinks()
        }

        return root
    }

    private fun searchDrinks(){
        val searchTerm = searchEditText.text.toString()
        if(searchTerm != null && searchTerm != "") {
            searchViewModel.userInputSearch.value = searchTerm
            navController.navigate(R.id.action_searchFragment_to_searchResultsFragment2)
        }
    }

}
