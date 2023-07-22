package com.ntk.testforstarwars.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ntk.testforstarwars.models.People
import com.ntk.testforstarwars.models.Starhip

@Dao
interface StarhipFavoriteDao {
    @Insert
    suspend fun insert(item: Starhip)

    @Query("SELECT * FROM starship_favorite_table")
    fun getAll(): List<Starhip>

    @Delete
    suspend fun delete(item: Starhip)
}