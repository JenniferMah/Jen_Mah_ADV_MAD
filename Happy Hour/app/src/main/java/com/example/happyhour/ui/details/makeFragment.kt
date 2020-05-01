package com.example.happyhour.ui.details

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
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        sharedSearchViewModel.selectedDrink.observe(viewLifecycleOwner, Observer {
            //ADD NULL CHECK or elvis op
            drinkInstructions = it.strInstructions!!
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink //These might have to change for favorites funcationalit
        })

        val root = inflater.inflate(R.layout.fragment_make, container, false)
        //References to the different items
        val makeDrinkButton = root.findViewById<Button>(R.id.Drank)
        val tellMeAJokeButton = root.findViewById<Button>(R.id.JokeButton)
        val makeTextView = root.findViewById<TextView>(R.id.makeTextView)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        sharedSearchViewModel.joke.observe(viewLifecycleOwner, Observer {
            if(it != sharedSearchViewModel.currentJoke){
                sharedSearchViewModel.currentJoke = it
                makeTextView.text = it.joke
            }
        })

        makeDrinkButton.setOnClickListener {
            if (makeDrinkButton.text == "Make My Drink"){
                makeDrinkButton.text = "Finish Drink"
                makeTextView.text = drinkInstructions
            }else{
                Finished()
            }
        }

        tellMeAJokeButton.setOnClickListener{
            //trigger jokes API call
            sharedSearchViewModel.getjoke()


        }

        return root
    }

    private fun Finished(){
        navController.navigate(R.id.action_makeFragment_to_searchFragment)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Happy Hour"

    }


}
