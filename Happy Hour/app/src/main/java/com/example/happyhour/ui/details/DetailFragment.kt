package com.example.happyhour.ui.details

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.happyhour.R
import com.example.happyhour.ui.SharedSearchViewModel
import kotlinx.android.synthetic.main.fragment_search_results.*


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var sharedSearchViewModel: SharedSearchViewModel
    var drinkIngredientList = mutableListOf<String>()
    private lateinit var navController: NavController
    private lateinit var drinkName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedSearchViewModel = ViewModelProvider(requireActivity()).get(SharedSearchViewModel::class.java)
        //references to views
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        val drinkTitleTextView = root.findViewById<TextView>(R.id.TitleTextView)
        val ingredientListView = root.findViewById<RecyclerView>(R.id.ingredientsListView)
        val makeButton = root.findViewById<Button>(R.id.MakeDrinkButton)

        //ALLOW OPTIONS MENU
        setHasOptionsMenu(true)



        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val imageView = root.findViewById<ImageView>(R.id.drinkImageView)
        //OBSERVER
        sharedSearchViewModel.selectedDrink.observe(viewLifecycleOwner, Observer {
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink
            drinkName = it.strDrink.toString()
            drinkTitleTextView.text = drinkName
            drinkIngredientList.clear()
            //check if null and add to recycler view
            checkNull(it.strMeasure1,it.strIngredient1)
            checkNull(it.strMeasure2,it.strIngredient2)
            checkNull(it.strMeasure3,it.strIngredient3)
            checkNull(it.strMeasure4,it.strIngredient4)
            checkNull(it.strMeasure5,it.strIngredient5)
            checkNull(it.strMeasure6,it.strIngredient6)
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


    private fun checkNull(measure:String?, item:String?) {
        if(measure != null && item != null){
            drinkIngredientList.add("$measure $item")
        }else if(item != null){
            drinkIngredientList.add(item)
        }
    }
    private fun makeDrink(){
        navController.navigate(R.id.action_detailFragment_to_makeFragment)

    }

}
