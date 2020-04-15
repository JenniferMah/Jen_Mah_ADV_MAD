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
    val drinkDetails = drinksRepo.drinkData

    init {
        selectedDrink.observeForever(drinksRepo.drinkSelectedObserver)

    }

    override fun onCleared() {
        selectedDrink.removeObserver(drinksRepo.drinkSelectedObserver)
        super.onCleared()
    }

}