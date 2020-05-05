package com.example.happyhour.ui.search

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.happyhour.R
import com.example.happyhour.data.cocktail.DrinksDetails

class SearchRecyclerAdapter(val context: Context, var drinkList: List<DrinksDetails>, val itemListener: DrinkItemListener) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {


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

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#f0fffe"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
    }

    interface DrinkItemListener {
        fun onDrinkItemClick(drink: DrinksDetails)
    }
}