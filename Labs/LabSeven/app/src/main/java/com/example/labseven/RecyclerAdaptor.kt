package com.example.labseven

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labseven.data.models.GroceryMember

class RecyclerAdaptor(var groceryList:List<GroceryMember>, val itemListener:GroceryListener) : RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>()  {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemTextView)
        val storeLocation: TextView = itemView.findViewById(R.id.locationTextView)
        val quanity: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("onCreate", groceryList.size.toString())
        val newItemView = LayoutInflater.from(parent.context).inflate(R.layout.grocery_list_item, parent, false)
        return ViewHolder(newItemView)
    }
    override fun getItemCount() = groceryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val currentItem = groceryList[position]
        holder.itemName.text = currentItem.itemName
        holder.storeLocation.text = currentItem.itemLocation
        holder.quanity.text = currentItem.itemQuantity.toString()

        holder.itemView.setOnClickListener {
            itemListener.onMemberClicked(currentItem.item_id)
        }
    }


    interface GroceryListener{
        fun onMemberClicked(member_id: Int)
    }
}