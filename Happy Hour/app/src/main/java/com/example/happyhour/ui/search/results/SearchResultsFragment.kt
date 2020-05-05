package com.example.happyhour.ui.search.results

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.happyhour.R
import com.example.happyhour.data.cocktail.DrinksDetails
import com.example.happyhour.ui.SharedSearchViewModel
import com.example.happyhour.ui.search.SearchRecyclerAdapter
import java.util.*
import kotlin.concurrent.schedule

/**
 * A simple [Fragment] subclass.
 */
class SearchResultsFragment : Fragment(),
    SearchRecyclerAdapter.DrinkItemListener {
    private lateinit var searchViewModel: SharedSearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var loadingBar: ProgressBar


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val root = inflater.inflate(R.layout.fragment_search_results, container, false)

        constraintLayout = root.findViewById(R.id.resultConstraintLayout)
        loadingBar = ProgressBar(requireContext())
        loadingBar.id = 1
        constraintLayout.addView(loadingBar)
        var constraints = ConstraintSet()
        constraints.clone(constraintLayout)
        constraints.connect(loadingBar.id, ConstraintSet.RIGHT, constraintLayout.id, ConstraintSet.RIGHT, 10)
        constraints.connect(loadingBar.id, ConstraintSet.LEFT, constraintLayout.id, ConstraintSet.LEFT, 10)
        constraints.connect(loadingBar.id, ConstraintSet.TOP, constraintLayout.id, ConstraintSet.TOP, 30)
        constraints.applyTo(constraintLayout)


        recyclerView = root.findViewById(R.id.recyclerView)


        searchViewModel.drinksData.observe(viewLifecycleOwner, Observer {
            var adapter = SearchRecyclerAdapter(requireContext(), listOf<DrinksDetails>(),this)

            if (it !=null ){
                Log.i("TEST",it.toString())
                adapter = SearchRecyclerAdapter(
                    requireContext(),
                    it,
                    this
                )

            }else{
                Handler().postDelayed({
                    NotValid()
                }, 1000)

            }
            isDoneLoading(false)
            recyclerView.adapter = adapter

        })
        // Inflate the layout for this fragment
        return root
    }

    fun NotValid(){
        Toast.makeText(requireContext(),"No Cocktail Matching that Description", Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_searchResultsFragment_to_searchFragment)
    }
    private fun isDoneLoading(loading: Boolean) {
        if(loading) {
            recyclerView.visibility = View.INVISIBLE
            loadingBar.visibility = View.VISIBLE

        } else if(!loading){
            recyclerView.visibility = View.VISIBLE
            loadingBar.visibility = View.GONE
        } else {
            loadingBar.visibility = View.GONE
        }
    }

    override fun onDrinkItemClick(drink: DrinksDetails) {
        searchViewModel.selectedDrink.value = drink
        navController.navigate(R.id.action_searchResultsFragment_to_detailFragment2)


    }

}
