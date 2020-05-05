package com.example.happyhour.data.cocktail

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.happyhour.DRINKS_BASE_URL
import com.example.happyhour.LOG_TAG
import com.example.happyhour.utils.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.URLEncoder

class DrinksRepository(val app: Application) {
   // private val listType = Types.newParameterizedType(List::class.java, DrinksDetails::class.java)
    val drinkData = MutableLiveData<List<DrinksDetails>>()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(DRINKS_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private var service: cocktailDBService

    init {
        service = retrofit.create(cocktailDBService::class.java)
    }

    val searchTermEntered = Observer<String> {
        CoroutineScope(Dispatchers.IO).launch {
            getDrinksData(it)
        }
    }

    //get the raw text from our json file and update the LiveData object with the parsed data
    @WorkerThread
    private suspend fun getDrinksData(searchTerm: String) {
        if(NetworkHelper.networkConnected(app)) {
            val response = service.searchCocktails(searchTerm).execute()
            if(response.body() != null ) {
                val responseBody = response.body()
                drinkData.postValue(responseBody?.drinks?.toList() as List<DrinksDetails>?)
            } else {
                Log.i("TEST", "Could not search drinks. Error code: ${response.code()}")
            }
        }
    }
}