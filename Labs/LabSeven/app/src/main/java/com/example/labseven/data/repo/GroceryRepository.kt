package com.example.labseven.data.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.labseven.data.AppDatabase
import com.example.labseven.data.database.saved.GroceryMember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryRepository(val app:Application) {
    private val db = AppDatabase.getDatabase(app)
    private val groceryDAO = db.groceryDAO()
    val allGroceryList: LiveData<List<GroceryMember>> = groceryDAO.getAllGrocery()

    fun additem(item: com.example.labseven.data.models.GroceryMember){
        CoroutineScope(Dispatchers.IO).launch {
            groceryDAO.insertGrocery(item.getGrocery())
            Log.i("TEST", "add grocery ${item}")
        }
    }

    fun removeitem(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            groceryDAO.removeGrocery(id)
        }
    }


}