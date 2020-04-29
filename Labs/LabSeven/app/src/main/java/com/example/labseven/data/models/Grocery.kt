package com.example.labseven.data.models

import java.util.*

data class GroceryMember(
    val item_id: Int,
    val itemName: String,
    val itemQuantity: Int,
    val itemLocation: String,
    val itemAddedDate: Date
){
    fun getGrocery(): GroceryMember{
        return GroceryMember(
            item_id,
            itemName,
            itemQuantity,
            itemLocation ,
            itemAddedDate
        )
    }
    companion object{
        fun fromRoomGrocery(grocery: GroceryMember): com.example.labseven.data.database.saved.GroceryMember{
            return com.example.labseven.data.database.saved.GroceryMember(
                grocery.item_id,
                grocery.itemName,
                grocery.itemQuantity,
                grocery.itemLocation,
                grocery.itemAddedDate
            )
        }
    }

}