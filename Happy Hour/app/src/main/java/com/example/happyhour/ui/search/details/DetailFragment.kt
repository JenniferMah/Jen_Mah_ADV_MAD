package com.example.happyhour.ui.search.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.happyhour.R
import com.example.happyhour.ui.SharedSearchViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var sharedSearchViewModel: SharedSearchViewModel
    val drinkIngredientList = mutableListOf<String>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //references to views
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        val drinkTitleTextView = root.findViewById<TextView>(R.id.TitleTextView)
        val ingredientListView = root.findViewById<RecyclerView>(R.id.ingredientsListView)
        val makeButton = root.findViewById<Button>(R.id.MakeDrinkButton)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val imageView = root.findViewById<ImageView>(R.id.drinkImageView)

        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        //OBSERVER
        sharedSearchViewModel.selectedDrink.observe(viewLifecycleOwner, Observer {
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink
            drinkTitleTextView.text = it.strDrink
            //check if null and add to recycler view
            checkNull(it.strMeasure1,it.strIngredient1)
            checkNull(it.strMeasure2,it.strIngredient2)
            checkNull(it.strMeasure3,it.strIngredient3)
            checkNull(it.strMeasure4,it.strIngredient4)
            checkNull(it.strMeasure5,it.strIngredient5)
            checkNull(it.strMeasure6,it.strIngredient6)
            checkNull(it.strMeasure7,it.strIngredient7)
            checkNull(it.strMeasure8,it.strIngredient8)
            checkNull(it.strMeasure9,it.strIngredient9)
            checkNull(it.strMeasure10,it.strIngredient10)

            val adapter = DetailRecyclerAdapter(requireContext(), drinkIngredientList)
            ingredientListView.adapter = adapter

            Glide.with(this)
                .load(it.strDrinkThumb)
                .into(imageView)
        })

        makeButton.setOnClickListener {
            makeDrink()
        }

        return root
    }

    private fun checkNull(measure:String?, item:String?) {
        if(measure != null && item != null){
            drinkIngredientList.add(measure + " " + item)
        }else if(item != null){
            drinkIngredientList.add(item)
        }
    }
    private fun makeDrink(){
        Log.i("TEST","REACH")
        navController.navigate(R.id.action_detailFragment_to_makeFragment)

    }

}
