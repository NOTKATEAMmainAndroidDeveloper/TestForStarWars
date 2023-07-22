package com.ntk.testforstarwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starship_favorite_table")
data class Starhip(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var name: String,
    var model: String,
    var manufacturer: String,
    var passengers: String,

    var url: String
)
