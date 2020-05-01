package com.example.happyhour.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.happyhour.data.cocktail.DrinksDetails
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavesRepo(val app: Application) {
    private val db = Firebase.firestore

    val favoriteList = MutableLiveData<List<DrinksDetails>>() //list of favorite drinks and the details?
    val drinkIsFavorite = MutableLiveData<Boolean>() //This will determine if it is faves drink or not

    init{
        db.collection("favoriteDrinks").addSnapshotListener { snapshot, e ->
            if (e!= null){
                Log.e("TEST", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                parseAllData(snapshot)
            } else {
                Log.d("TEST", "Current data: null")
            }
            }
        }


    private fun parseAllData(result: QuerySnapshot) {
        val allFavoriteDrinks = mutableListOf<DrinksDetails>()
        for(doc in result) {
            //get the data from the document
            val id: String = doc.id.toString() //might need to be idDrink HELP
            val name: String = doc.getString("strDrink")!!
            val imgae: String = doc.getString("strDrinkThumb")!!
            val strIngredient1: String = doc.getString("strIngredient1")!!
            val strIngredient2: String = doc.getString("strIngredient2")!!
            val strIngredient3: String = doc.getString("strIngredient3")!!
            val strIngredient4: String = doc.getString("strIngredient4")!!
            val strIngredient5: String = doc.getString("strIngredient5")!!
            val strIngredient6: String = doc.getString("strIngredient6")!!

            val strMeasure1: String = doc.getString("strMeasure1")!!
            val strMeasure2: String = doc.getString("strMeasure2")!!
            val strMeasure3: String = doc.getString("strMeasure3")!!
            val strMeasure4: String = doc.getString("strMeasure4")!!
            val strMeasure5: String = doc.getString("strMeasure5")!!
            val strMeasure6: String = doc.getString("strMeasure6")!!
        }

        Log.i("TEST", "allData: $allFavoriteDrinks")
        favoriteList.value =  allFavoriteDrinks

    }

    fun isDrinkFavorited(id: Int) {
        //See if it's favorites?
    }
    fun removeDrinkFromFavorites(id: Int) {
        db.collection("favoriteDrinks").document(id.toString()).delete()
    }

}