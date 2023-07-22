package com.ntk.testforstarwars.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ntk.testforstarwars.dao.PeopleFavoriteDao
import com.ntk.testforstarwars.dao.StarhipFavoriteDao
import com.ntk.testforstarwars.models.People
import com.ntk.testforstarwars.models.Starhip

@Database(
    entities = [
        People::class, Starhip::class
    ],
    version = 2
)
abstract class FavoriteDb : RoomDatabase() {
    abstract fun peopleFavoriteDao(): PeopleFavoriteDao
    abstract fun starshipFavoriteDao(): StarhipFavoriteDao
}
