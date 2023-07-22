package com.ntk.testforstarwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@TypeConverters(InspectionConverter::class)
@Entity(tableName = "people_favorite_table")
data class People(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var name: String,
    var gender: String,

    @TypeConverters()
    var starships: List<String>,

    var url: String
): java.io.Serializable


class InspectionConverter {
    @TypeConverter
    fun stringToObject(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<Any>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
