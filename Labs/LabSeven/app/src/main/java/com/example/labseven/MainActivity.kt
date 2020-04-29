package com.example.labseven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.labseven.data.GroceryViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private lateinit var groceryVM: GroceryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        groceryVM = ViewModelProvider(this).get(GroceryViewModel::class.java)

        groceryVM.groceryList.observe(this, Observer {
            Log.i("TEST","hello")
            Log.i("TEST",it.toString())
        })


    }
}
