package com.example.labseven.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.labseven.data.database.saved.GroceryDAO
import com.example.labseven.data.database.saved.GroceryMember

@Database(entities = [GroceryMember::class],
    version = 3, //CHANGE
    exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDatabase: RoomDatabase() {
    abstract fun groceryDAO(): GroceryDAO

    companion object{
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "grocery_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}
