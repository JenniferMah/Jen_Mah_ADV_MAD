package com.example.happyhour.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.happyhour.data.FavesRepo
import com.example.happyhour.data.jokes.JokesDetails
import com.example.happyhour.data.jokes.JokesRepo
import com.example.happyhour.data.cocktail.DrinksDetails
import com.example.happyhour.data.cocktail.DrinksRepository

class SharedSearchViewModel(app: Application) : AndroidViewModel(app) {

    private val drinksRepo = DrinksRepository(app)

    val drinksData = drinksRepo.drinkData
    val selectedDrink = MutableLiveData<DrinksDetails>()
    val userInputSearch = MutableLiveData<String>()
    val jokesRepo = JokesRepo(app)
    val joke = jokesRepo.jokeData
    var currentJoke = JokesDetails("")
    val favesRepo = FavesRepo(app)
    val favDrinksList: MutableLiveData<List<DrinksDetails>> = favesRepo.favoriteList
    var validSearch = true

    init {
        userInputSearch.observeForever(drinksRepo.searchTermEntered)
    }

    fun getjoke(){
        jokesRepo.callJokes()
    }

    fun addFaves(){
        favesRepo.addDrinkFirebase(selectedDrink.value!!)
    }

    fun isfaves():Boolean{
        return favesRepo.isDrinkFavorited(selectedDrink.value?.idDrink!!)
    }

    fun delete(id:String){
        favesRepo.removeDrinkFromFavorites(id)
    }

    override fun onCleared() {
        userInputSearch.removeObserver(drinksRepo.searchTermEntered)
        super.onCleared()
    }

}