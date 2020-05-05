package com.example.happyhour.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyhour.R


class DetailRecyclerAdapter(val context: Context, var drinkIngredientList: List<String>): RecyclerView.Adapter<DetailRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ingredientTextView: TextView = itemView.findViewById<TextView>(R.id.DrinkIngrdientTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRecyclerAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ingredient_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = drinkIngredientList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentIngrdient = drinkIngredientList[position]
        holder.ingredientTextView.text = currentIngrdient
    }



}
