package com.example.happyhour.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.happyhour.data.repos.FavesRepo
import com.example.happyhour.data.jokes.JokesDetails
import com.example.happyhour.data.repos.JokesRepo
import com.example.happyhour.data.cocktail.DrinksDetails
import com.example.happyhour.data.repos.DrinksRepository

class SharedSearchViewModel(app: Application) : AndroidViewModel(app) {

    private val drinksRepo =
        DrinksRepository(app)

    var drinksData = drinksRepo.drinkData
    val selectedDrink = MutableLiveData<DrinksDetails>()
    val userInputSearch = MutableLiveData<String>()
    private val jokesRepo = JokesRepo(app)
    val joke = jokesRepo.jokeData
    var currentJoke = JokesDetails("")
    private val favesRepo = FavesRepo(app)
    val favDrinksList: MutableLiveData<List<DrinksDetails>> = favesRepo.favoriteList


    init {
        userInputSearch.observeForever(drinksRepo.searchTermEntered)
    }
    //thanks michael this fixes the problem with keeping the old null item when the item is invalid
    fun clear(){ drinksData.value =  listOf<DrinksDetails>() }


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