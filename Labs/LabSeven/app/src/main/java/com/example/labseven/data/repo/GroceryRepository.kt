package com.example.labseven.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.labseven.data.AppDatabase
import com.example.labseven.data.database.saved.GroceryMember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.coroutineContext

class GroceryRepository(val app:Application) {
    private val db = AppDatabase.getDatabase(app)
    private val groceryDAO = db.groceryDAO()
    val allGroceryList: LiveData<List<GroceryMember>> = groceryDAO.getAllGrocery()


    init{
        //dummy data to test
        CoroutineScope(Dispatchers.IO).launch {
            groceryDAO.insertGrocery(GroceryMember(1, "pasta", 3 , "starch", Date()))

        }

    }
}