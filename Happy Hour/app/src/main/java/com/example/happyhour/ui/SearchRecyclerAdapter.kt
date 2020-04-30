package com.example.happyhour.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyhour.R
import com.example.happyhour.data.DrinksDetails

class SearchRecyclerAdapter(val context: Context, val drinkList: List<DrinksDetails>, val itemListener: DrinkItemListener) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.drink_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = drinkList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curDrink = drinkList[position]
        holder.titleText.text = curDrink.strDrink

        holder.itemView.setOnClickListener {
            itemListener.onDrinkItemClick(curDrink)
        }
    }

    interface DrinkItemListener {
        fun onDrinkItemClick(drink: DrinksDetails)
    }
}