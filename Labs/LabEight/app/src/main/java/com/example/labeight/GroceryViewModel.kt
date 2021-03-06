package com.example.labeight

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.labeight.models.GroceryItem
import com.example.labeight.repo.GroceryRepository

class GroceryViewModel(app: Application) : AndroidViewModel(app){
    private val groceryRepo = GroceryRepository(app)
    val groceryList: MutableLiveData<List<GroceryItem>> = MutableLiveData()

    private val groceryListObserver  = Observer<List<GroceryItem>> {
        groceryList.value = groceryRepo.allGrocery
    }
    init{
        groceryRepo.groceryList.observeForever(groceryListObserver)
    }
    override fun onCleared() {
        groceryRepo.groceryList.removeObserver(groceryListObserver)
        super.onCleared()
    }

    fun addGrocery(item:GroceryItem) {
        groceryRepo.addGrocery(item)
    }

    fun removeGrocery(id: Int) = groceryRepo.removeGrocery(id)
}