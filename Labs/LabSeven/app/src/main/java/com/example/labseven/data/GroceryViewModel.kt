package com.example.labseven.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.labseven.data.database.saved.GroceryMember
import com.example.labseven.data.repo.GroceryRepository

class GroceryViewModel (app: Application) : AndroidViewModel(app){
    private val groceryRepo = GroceryRepository(app)
    val groceryList: MutableLiveData<List<com.example.labseven.data.models.GroceryMember>> = MutableLiveData()

    private val groceryListObserver  = Observer<List<GroceryMember>> {
        val allGrocery = mutableListOf<com.example.labseven.data.models.GroceryMember>()

        for (item in it) {
            allGrocery.add(com.example.labseven.data.models.GroceryMember.fromDatabaseGrocery(item))
        }

        groceryList.value = allGrocery
    }
    init{
        groceryRepo.allGroceryList.observeForever(groceryListObserver)
    }
    override fun onCleared() {
        groceryRepo.allGroceryList.removeObserver(groceryListObserver)
        super.onCleared()
    }

    fun addGrocery(item: com.example.labseven.data.models.GroceryMember) {
        groceryRepo.additem(item)
    }

    fun removeGrocery(id: Int) = groceryRepo.removeitem(id)

}