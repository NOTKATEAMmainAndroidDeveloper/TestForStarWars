package com.ntk.testforstarwars.dao

import androidx.room.*
import com.ntk.testforstarwars.models.People

@Dao
interface PeopleFavoriteDao {
    @Insert
    suspend fun insert(item: People)

    @Query("SELECT * FROM people_favorite_table")
    fun getAll(): List<People>

    @Query("SELECT * FROM people_favorite_table WHERE id=:id")
    fun get(id: String): People

    @Delete
    suspend fun delete(item: People)
}