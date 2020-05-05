package com.example.happyhour.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
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
    var drinkName = String()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)

        sharedSearchViewModel.selectedDrink.observe(viewLifecycleOwner, Observer {
            //ADD NULL CHECK or elvis op
            drinkInstructions = it.strInstructions!!
            drinkName = it.strDrink.toString()
            (activity as AppCompatActivity?)?.supportActionBar?.title = drinkName//These might have to change for favorites funcationalit
        })

        setHasOptionsMenu(true)

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
                finished()
            }
        }

        tellMeAJokeButton.setOnClickListener{
            //trigger jokes API call
            sharedSearchViewModel.getjoke()


        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        val item = menu.findItem(R.id.favoriteDrink)
        checkFaves(item)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.favoriteDrink){
            sharedSearchViewModel.addFaves()
            item.icon = ResourcesCompat.getDrawable(resources,R.drawable.heart_icon_full,null)
            Toast.makeText(requireContext(),"$drinkName added to list of favorite drinks!", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)

    }


    private fun checkFaves(menuItem: MenuItem){
        val isFaves = sharedSearchViewModel.isfaves()
        if (isFaves){
            menuItem.icon = ResourcesCompat.getDrawable(resources,R.drawable.heart_icon_full,null)
        }else{
            menuItem.icon = ResourcesCompat.getDrawable(resources,R.drawable.heart_icon,null)
        }
    }

    private fun finished(){
        navController.navigate(R.id.action_makeFragment_to_searchFragment)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Happy Hour"

    }


}
