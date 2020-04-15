package com.example.labsix.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.labsix.BASE_URL
import com.example.labsix.utils.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DrinksRepository(val app: Application) {
    private val listType = Types.newParameterizedType(List::class.java, DrinksDetails::class.java)
    val drinkData = MutableLiveData<List<DrinksDetails>>()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private var service:cocktailDBService

    val drinkSelectedObserver =  Observer<DrinksDetails> {
        getDrinksData()
    }

    init {
        getDrinksData()

        Log.i("Drinks","TEST")
        service = retrofit.create(cocktailDBService::class.java)
        Log.i("Drinks","AFTER")

        Log.i("Drinks","AFTER AFTER")

    }

    //get the raw text from our json file and update the LiveData object with the parsed data
    private fun getDrinksData() {
        val dataText = FileHelper.readTextFromAssets(app, "drink-data.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<DrinksDetails>> = moshi.adapter(listType)
        //update our LiveData object with the results of our parsing
        drinkData.value = adapter.fromJson(dataText) ?: emptyList()
    }
}