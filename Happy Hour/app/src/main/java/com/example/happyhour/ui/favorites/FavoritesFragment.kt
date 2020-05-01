package com.example.happyhour.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.happyhour.R


class FavoritesFragment : Fragment() {
    private lateinit var FavesViewModel:FavesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("TEST","LOADING")
        FavesViewModel = ViewModelProvider(this).get(FavesViewModel::class.java)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        return root
    }


}
