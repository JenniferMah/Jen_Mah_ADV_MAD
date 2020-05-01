package com.example.happyhour.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SharedFavoritesViewModel:ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is the Favorites Fragment"
    }
    val text: LiveData<String> = _text

}