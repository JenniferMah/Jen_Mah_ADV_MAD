package com.example.happyhour.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.happyhour.R
import com.example.happyhour.data.cocktail.DrinksDetails
import com.example.happyhour.ui.SharedSearchViewModel
import com.example.happyhour.ui.details.DetailFragment
import com.example.happyhour.ui.details.DetailRecyclerAdapter


class FavoritesFragment : Fragment(){
    private lateinit var favesViewModel:SharedFavoritesViewModel
    private lateinit var favRecyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var sharedSearchViewModel: SharedFavoritesViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favesViewModel = ViewModelProvider(this).get(SharedFavoritesViewModel::class.java)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedFavoritesViewModel::class.java)




        return root
    }


}
