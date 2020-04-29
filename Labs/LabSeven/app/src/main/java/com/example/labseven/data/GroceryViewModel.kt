package com.example.labseven.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.labseven.data.database.saved.GroceryMember
import com.example.labseven.data.repo.GroceryRepository

class GroceryViewModel (app: Application) : AndroidViewModel(app){
    private val groceryRepo = GroceryRepository(app)
    val groceryList = groceryRepo.allGroceryList
//    val groceryList: MutableLiveData<List<GroceryMember>> = MutableLiveData()

//    private val groceryListObserver  = Observer<List<com.example.labseven.data.database.saved.GroceryMember>> {
//        val allGrocery = mutableListOf<GroceryMember>()
//
//        for (item in it) {
//            allGrocery.add(GroceryMember.fromRoomGrocery(item))
//        }
//
//        groceryList.value = allGrocery
//    }
//    init{
//        groceryRepo.allGroceryList.observeForever(groceryListObserver)
//    }
//    override fun onCleared() {
//        groceryRepo.allGroceryList.removeObserver(groceryListObserver)
//        super.onCleared()
//    }
//
//    fun addGrocery(item: com.example.labseven.data.models.GroceryMember){
//        groceryRepo.additem(item)
//    }
//
//    fun removeGrocery(id: Int) = groceryList.removeitem(id)

}