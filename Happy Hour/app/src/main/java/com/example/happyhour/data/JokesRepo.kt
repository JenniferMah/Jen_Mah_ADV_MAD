package com.example.happyhour.data

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.happyhour.JOKES_BASE_URL
import com.example.happyhour.LOG_TAG
import com.example.happyhour.data.JokesDetails
import com.example.happyhour.data.jokesDBService
import com.example.happyhour.utils.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class JokesRepo(val app: Application) {
    // private val listType = Types.newParameterizedType(List::class.java, DrinksDetails::class.java)
    val jokeData = MutableLiveData<List<JokesDetails>>()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(JOKES_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private var service: jokesDBService

    init {
        service = retrofit.create(jokesDBService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            //getJokes("bar")
        }
    }

//    val searchTermEntered = Observer<String> {
//        CoroutineScope(Dispatchers.IO).launch {
//            getJokes(bar)
//        }
//    }

    //get the raw text from our json file and update the LiveData object with the parsed data
//    @WorkerThread
//    private suspend fun getJokes(searchTerm: String) {
//        if(NetworkHelper.networkConnected(app)) {
//            val response = service.searchJokes(searchTerm).execute()
//            if(response.body() != null) {
//                Log.i(LOG_TAG,"THIS MAKES IT INTO THE CALL")
//                val responseBody = response.body()
//                jokeData.postValue(responseBody?.results?.toList()) //THIS IS WHERE PROBLEMS ARE OCCURING
//            } else {
//                Log.e(LOG_TAG, "Could not search drinks. Error code: ${response.code()}")
//            }
//        }
//    }
}