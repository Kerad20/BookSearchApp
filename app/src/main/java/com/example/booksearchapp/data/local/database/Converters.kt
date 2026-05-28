package com.example.booksearchapp.data.local.database

import androidx.room.TypeConverter
import com.example.booksearchapp.data.remote.dto.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromAuthors(value: List<Author>): String =
        gson.toJson(value)

    @TypeConverter
    fun toAuthors(value: String): List<Author> =
        gson.fromJson(value, object : TypeToken<List<Author>>() {}.type)

    @TypeConverter
    fun fromStringList(value: List<String>): String =
        gson.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> =
        gson.fromJson(value, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromMap(value: Map<String, String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMap(value: String): Map<String, String> {
        return gson.fromJson(value, object : TypeToken<Map<String, String>>() {}.type)
    }
}