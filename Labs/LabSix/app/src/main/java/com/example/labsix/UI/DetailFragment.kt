package com.example.labsix.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.labsix.R

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var sharedSearchViewModel: SharedSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_detail, container, false)

        val drinkTitleTextView = root.findViewById<TextView>(R.id.TitleTextView)
        val instructionsTextView = root.findViewById<TextView>(R.id.instructionsTextView)
        val imageView = root.findViewById<ImageView>(R.id.drinkImageView)

        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        sharedSearchViewModel.selectedDrink.observe(viewLifecycleOwner, Observer {
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink
            drinkTitleTextView.text = it.strDrink
            instructionsTextView.text = it.strInstructions
            //SHOULD ADD THE PHOTO?
            Glide.with(this)
                .load(it.strDrinkThumb)
                .into(imageView)
        })


        return root
    }

}
