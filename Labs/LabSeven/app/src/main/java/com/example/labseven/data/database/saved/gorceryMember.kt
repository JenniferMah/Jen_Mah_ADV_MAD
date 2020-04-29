package com.example.labseven.data.database.saved

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "grocery_table")
data class GroceryMember(
    @PrimaryKey(autoGenerate = true) val item_id: Int = 0,
    val itemName: String,
    val itemQuantity:Int,
    val itemLocation: String,
    val itemAddedDate: Date
)