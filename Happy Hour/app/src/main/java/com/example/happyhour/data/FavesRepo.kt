package com.example.happyhour.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.happyhour.data.cocktail.DrinksDetails
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class FavesRepo(val app: Application) {
    private val db = Firebase.firestore

    val favoriteList = MutableLiveData<List<DrinksDetails>>() //list of favorite drinks and the details?

    init{
        db.collection("favoriteDrinks").addSnapshotListener { snapshot, e ->
            if (e!= null){
                Log.e("TEST", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                parseAllData(snapshot)
                //send an object to firebase and get it back instead.

            } else {
                Log.d("TEST", "Current data: null")
            }
            }
        }

    private fun parseAllData(result: QuerySnapshot) {
        val allFavoriteDrinks = mutableListOf<DrinksDetails>()
        for(doc in result) {
            val currentDrink = doc.toObject<DrinksDetails>()
            currentDrink.idDrink = doc.id
            allFavoriteDrinks.add(currentDrink) //this should be a drinks details object
       }
        favoriteList.value =  allFavoriteDrinks

   }
    fun addDrinkFirebase(drink:DrinksDetails){
        db.collection("favoriteDrinks").document(drink.idDrink!!).set(drink)
    }

    fun isDrinkFavorited(id: String): Boolean {
        if (favoriteList.value != null){
            for(i in favoriteList.value!!){
                if (id == i.idDrink) {
                    return true
                }
            }
            return false
        }
        return false

    }
    fun removeDrinkFromFavorites(id: String) {
        db.collection("favoriteDrinks").document(id).delete()
    }

}