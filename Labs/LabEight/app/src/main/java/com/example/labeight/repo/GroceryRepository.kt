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
            val id: Int = (doc.get("id") as Long).toInt()
            val location: String = doc.getString("location")!!
            val name: String = doc.getString("name")!!
            //THIS IS NOT WORKING
            //val quantity: Int = (doc.get("quantity") as String).toInt()

            //add item to the mutable list
            allGrocery .add(GroceryItem(id, location,name, 1))
        }
        Log.i("TEST", "All data: $allGrocery")
        groceryList.value = allGrocery
    }

    fun removeGrocery(id: Int) {
        db.collection("GroceryList").document(id.toString()).delete()
    }

    fun addGrocery(grocery: GroceryItem) {
        val recipeMap = recipeDetailsToHashMap(grocery)

        db.collection("favorites").document(grocery.id.toString())
            .set(recipeMap)
            .addOnSuccessListener {
                Log.i("TEST", "Added favorite success!")
            }
            .addOnFailureListener { exception ->
                Log.w("TEST", "Error adding document.", exception)
            }
    }
    private fun recipeDetailsToHashMap(recipe: RecipeDetails): HashMap<String, *> {
        val map = hashMapOf(
            "id" to recipe.id,
            "title" to recipe.title,
            "summary" to recipe.summary,
            "image" to recipe.image,
            "readyInMinutes" to recipe.readyInMinutes.toString(),
            "servings" to recipe.servings.toString(),
            "ingredients" to ingredientsToArrayOfMaps(recipe.extendedIngredients),
            "instructions" to instructionsToArrayOfMaps(recipe.analyzedInstructions[0].steps)
        )
        return map
    }
//
//    private fun ingredientsToArrayOfMaps(ingredients: Set<Ingredient>): ArrayList<HashMap<String, *>> {
//        val ingredientArrayList = ArrayList<HashMap<String, *>>()
//
//        for(ingredient in ingredients) {
//            ingredientArrayList.add(hashMapOf(
//                "originalString" to ingredient.originalString,
//                "name" to ingredient.name,
//                "amount" to ingredient.amount.toString(),
//                "unit" to ingredient.unit
//            ))
//        }
//        return ingredientArrayList
//    }
//
//    private fun instructionsToArrayOfMaps(instructions: List<Instruction>): ArrayList<HashMap<String, *>> {
//        val instructionArrayList = ArrayList<HashMap<String, *>>()
//
//        for(instruction in instructions) {
//            instructionArrayList.add(hashMapOf(
//                "number" to instruction.number.toString(),
//                "step" to instruction.step
//            ))
//        }
//
//        return instructionArrayList
//    }

}