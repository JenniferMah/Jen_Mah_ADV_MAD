package com.example.happyhour.ui.favorites

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.*
import com.example.happyhour.data.FavesRepo
import com.example.happyhour.data.cocktail.DrinksDetails

class SharedFavoritesViewModel(app: Application) : AndroidViewModel(app)  {
    private val favesRepo = FavesRepo(app)

    val favDrinksList: MutableLiveData<List<DrinksDetails>> = favesRepo.favoriteList

    var numberOfCurrentDrinks = 0

    //get selected favorites details and update live data
    fun favSelected(id:Int) {
        favesRepo.removeDrinkFromFavorites(id)
    }

    //pass new favorite to repo class for transform and insertion
    fun addFavoriteDrink(recipe: DrinksDetails) {
       //add drink from the star
    }

    val isFavorite = favesRepo.drinkIsFavorite

    fun isDrinkFavorited(id: Int) {
        favesRepo.isDrinkFavorited(id)
    }

}