package com.example.happyhour.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.happyhour.R
import com.example.happyhour.data.cocktail.DrinksDetails
import com.example.happyhour.ui.SharedSearchViewModel
import com.example.happyhour.ui.details.DetailFragment
import com.example.happyhour.ui.details.DetailRecyclerAdapter
import com.example.happyhour.ui.search.SearchRecyclerAdapter


class FavoritesFragment : Fragment(),
    SearchRecyclerAdapter.DrinkItemListener {
    private lateinit var navController: NavController
    private lateinit var sharedSearchViewModel: SharedSearchViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        val rec = root.findViewById<RecyclerView>(R.id.favoritesRecyclerView)
        var adapter = SearchRecyclerAdapter(requireContext(), emptyList<DrinksDetails>(),this)
        rec.adapter = adapter

        sharedSearchViewModel.favDrinksList.observe(viewLifecycleOwner, Observer {
            //reuse details recycler
            val favesDrinks = mutableListOf<DrinksDetails>()
            for( i in it){
                favesDrinks.add(i)
            }

            adapter.drinkList = favesDrinks
            adapter.notifyDataSetChanged()
        })

        return root
    }



    override fun onDrinkItemClick(drink: DrinksDetails) {
        //add toast
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Do you want to remove this drink from your favorite drink list?")
            .setCancelable(false)
            .setPositiveButton("YES") { dialog, _ ->
                sharedSearchViewModel.delete(drink.idDrink!!)
                Toast.makeText(requireContext(),"Recipe removed from Favorites!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("NO") {
                    dialog, _ -> dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Remove Drink From Favorites List")
        alert.show()
    }


}
