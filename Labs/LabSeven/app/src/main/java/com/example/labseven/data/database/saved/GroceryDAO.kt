package com.example.labseven.data.database.saved

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.lang.reflect.Member

@Dao
interface GroceryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGrocery(groceryMember: GroceryMember)

    @Query("SELECT * FROM grocery_table")
    fun getAllGrocery(): LiveData<List<GroceryMember>>

    @Query("DELETE FROM grocery_table WHERE item_id = :id")
    fun removeGrocery(id: Int)
}


