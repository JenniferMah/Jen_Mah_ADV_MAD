package com.example.happyhour.ui.search.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.happyhour.R
import com.example.happyhour.ui.SharedSearchViewModel

/**
 * A simple [Fragment] subclass.
 */
class makeFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var sharedSearchViewModel: SharedSearchViewModel
    var drinkInstructions = String()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("TEST","TEST")
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)
        sharedSearchViewModel.selectedDrink.observe(viewLifecycleOwner, Observer {
            drinkInstructions = it.strInstructions
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink


        })

        val root = inflater.inflate(R.layout.fragment_make, container, false)
        //References to the different items
        val makeDrinkButton = root.findViewById<Button>(R.id.Drank)
        val tellMeAJokeButton = root.findViewById<Button>(R.id.JokeButton)
        val makeTextView = root.findViewById<TextView>(R.id.makeTextView)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)


        makeDrinkButton.setOnClickListener {
            if (makeDrinkButton.text == "Make My Drink"){
                makeDrinkButton.text = "Finish Drink"
                makeTextView.text = drinkInstructions
            }else{
                Finished()
            }
        }

        tellMeAJokeButton.setOnClickListener{
            //HELP: MAKE API CALL TO GET JOKE Not sure if I should add an observer?
            Log.i("TEST", "TELL EM A JOKE")
        }

        return root
    }

    private fun Finished(){
        navController.navigate(R.id.action_makeFragment_to_searchFragment)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Happy Hour"

    }


}
