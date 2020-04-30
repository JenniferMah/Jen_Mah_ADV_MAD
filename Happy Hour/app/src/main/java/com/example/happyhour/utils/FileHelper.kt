package com.example.happyhour.utils

import android.content.Context

class FileHelper {
    //functions like a static method
    companion object {
        fun readTextFromAssets(context: Context, filename: String): String {
            return context.assets.open(filename).use { inputStream ->
                inputStream.bufferedReader().use {
                    it.readText()
                }
            }

        }
    }
}