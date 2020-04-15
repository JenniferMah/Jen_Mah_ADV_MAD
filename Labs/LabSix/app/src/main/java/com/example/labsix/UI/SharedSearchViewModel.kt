package com.example.labsix.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.labsix.data.DrinksDetails
import com.example.labsix.data.DrinksRepository

class SharedSearchViewModel(app: Application) : AndroidViewModel(app) {

    private val drinksRepo = DrinksRepository(app)

    val drinksData = drinksRepo.drinkData
    val selectedDrink = MutableLiveData<DrinksDetails>()
    val userInputSearch = MutableLiveData<String>()

    init {
        userInputSearch.observeForever(drinksRepo.searchTermEntered)

    }

    override fun onCleared() {
        userInputSearch.removeObserver(drinksRepo.searchTermEntered)
        super.onCleared()
    }

}