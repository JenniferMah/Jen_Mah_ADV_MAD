package com.example.labseven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.example.labseven.data.GroceryViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.labseven.data.models.GroceryMember
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), RecyclerAdaptor.GroceryListener {
    private lateinit var groceryVM: GroceryViewModel
    private lateinit var listRecyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdaptor
    private lateinit var itemNameInput: EditText
    private lateinit var locationInput: EditText
    private lateinit var numberInput:EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        groceryVM = ViewModelProvider(this).get(GroceryViewModel::class.java)
        listRecyclerView = this.findViewById(R.id.recyclerView)
        itemNameInput = this.findViewById(R.id.itemName)
        locationInput = this.findViewById(R.id.storeLocation)
        numberInput = this.findViewById(R.id.editText)
        submitButton = this.findViewById(R.id.submitButton)
        adapter = RecyclerAdaptor(emptyList<GroceryMember>(), this)
        listRecyclerView.adapter = adapter

        groceryVM.groceryList.observe(this, Observer {
            adapter.groceryList = it
            adapter.notifyDataSetChanged() //tell adapter it has changed
        })

        submitButton.setOnClickListener{
            addingFunction()

        }



    }

    fun addingFunction(){
        val foodName = itemNameInput.text.toString()
        val number = numberInput.text.toString()
        val location = locationInput.text.toString()
        val calDate = Calendar.getInstance().time
        if (foodName != null && foodName != "" && number != null && number != "" && location != null && location != ""){
            groceryVM.addGrocery(GroceryMember(0,foodName,Integer.parseInt(number),location,calDate))
        }

    }

    override fun onMemberClicked(member_id: Int) {
        groceryVM.removeGrocery(member_id)
    }
}
