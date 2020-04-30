package com.example.labeight.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.labeight.models.GroceryItem
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GroceryRepository(val app:Application) {
    private val db = Firebase.firestore
    var allGrocery = mutableListOf<GroceryItem>()
    //list of items in grocery list
    val groceryList = MutableLiveData<List<GroceryItem>>()

    init{
        db.collection("GroceryList").addSnapshotListener{snapshot, e ->
            if( e != null){
                Log.i("TEST","firebase listen failed:",e)
                return@addSnapshotListener
            }else{
                if(snapshot != null){
                    parseAllData(snapshot)
                }else{
                    Log.i("TEST", "data is null")
                }
            }
        }
    }

    private  fun parseAllData(result:QuerySnapshot){
        for (doc in result){
            //get all of the data and cast to correct types
            val id: Int = doc.id.toInt()
            val location: String = doc.getString("location")!!
            val name: String = doc.getString("name")!!
            //THIS IS NOT WORKING
            //val quantity: Int = (doc.get("quantity") as String).toInt()

            //add item to the mutable list
            allGrocery.add(GroceryItem(id, location,name, 1))
        }
        Log.i("TEST", "All data: $allGrocery")
        groceryList.value = allGrocery
    }

    fun removeGrocery(id: Int) {
        db.collection("GroceryList").document(id.toString()).delete()
    }

    fun addGrocery(grocery: GroceryItem) {
        val recipeMap = recipeDetailsToHashMap(grocery)

        db.collection("GroceryList").document(grocery.id.toString())
            .set(recipeMap)
            .addOnSuccessListener {
                Log.i("TEST", "Added groceries success!")
            }
            .addOnFailureListener { exception ->
                Log.w("TEST", "Error adding document.", exception)
            }
    }

    private fun recipeDetailsToHashMap(grocery:GroceryItem ): HashMap<String, *> {
        val map = hashMapOf(
            "id" to grocery.id,
            "location" to grocery.location,
            "name" to grocery.name,
            "quantity" to grocery.quantity
        )
        return map
    }

}