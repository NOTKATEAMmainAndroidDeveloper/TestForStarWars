package com.ntk.testforstarwars.moduls

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.room.Room
import com.android.volley.toolbox.Volley
import com.ntk.testforstarwars.acts.addVolleyRequestApi
import com.ntk.testforstarwars.enums.MainSearchTypeEnum
import com.ntk.testforstarwars.localDb.FavoriteDb
import com.ntk.testforstarwars.models.People
import com.ntk.testforstarwars.models.Starhip
import kotlinx.coroutines.*

class Repository(context: Context) {
    var peopleList = SnapshotStateList<People>()
    var starhipList = SnapshotStateList<Starhip>()

    var favoritePeopleList = SnapshotStateList<People>()
    var favoriteStarhipList = SnapshotStateList<Starhip>()

    val volleyQueue = Volley.newRequestQueue(context)

    val localDatabase = Room.databaseBuilder(
        context,
        FavoriteDb::class.java, "favorite_db"
    ).fallbackToDestructiveMigration().build()

    init {
        getDataFromApi()
        if(InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED)getFavoriteDataFromLocalDB()

        instance = this
    }

    fun isItemInFavorite(name: String): Boolean{
        return favoritePeopleList.firstOrNull {
            it.name == name
        } != null || favoriteStarhipList.firstOrNull {
            it.name == name
        } != null
    }

    @OptIn(DelicateCoroutinesApi::class)
    inline fun <reified T> saveFavorite(item: T){
        if(InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED)GlobalScope.async {
            when{
                T::class.java.isAssignableFrom(People::class.java) -> {
                    localDatabase.peopleFavoriteDao().insert(item as People)

                    favoritePeopleList.add(item as People)
                }

                T::class.java.isAssignableFrom(Starhip::class.java) -> {
                    localDatabase.starshipFavoriteDao().insert(item as Starhip)

                    favoriteStarhipList.add(item as Starhip)
                }
                else -> {}
            }
        }.start()
    }

    @OptIn(DelicateCoroutinesApi::class)
    inline fun <reified T> deleteFavorite(item: T){
        GlobalScope.async {
            when{
                T::class.java.isAssignableFrom(People::class.java) -> {
                    localDatabase.peopleFavoriteDao().delete(item as People)

                    favoritePeopleList.remove(item as People)
                }

                T::class.java.isAssignableFrom(Starhip::class.java) -> {
                    localDatabase.starshipFavoriteDao().delete(item as Starhip)

                    favoriteStarhipList.remove(item as Starhip)
                }
                else -> {}
            }
        }.start()
    }

     @OptIn(DelicateCoroutinesApi::class)
     fun getDataFromApi(){
         GlobalScope.async {
             async {
                 addVolleyRequestApi<People>(volleyQueue, "https://swapi.dev/api/people/", peopleList)
             }.start()

             async {
                 addVolleyRequestApi<Starhip>(volleyQueue, "https://swapi.dev/api/starships/", starhipList)
             }.start()
         }.start()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun searchDataFromApi(
        name: String,
        _searchType: MutableState<MainSearchTypeEnum>
    ){
        GlobalScope.async {

            when(_searchType.value){
                MainSearchTypeEnum.All -> {
                    async {
                        instance.peopleList.clear()

                        addVolleyRequestApi<People>(volleyQueue,
                            "https://swapi.dev/api/people/?search=$name", peopleList)
                    }.start()

                    async {
                        instance.starhipList.clear()

                        addVolleyRequestApi<Starhip>(volleyQueue,
                            "https://swapi.dev/api/starships/?search=$name", starhipList)
                    }.start()
                }
                MainSearchTypeEnum.Person -> {
                    instance.peopleList.clear()

                    addVolleyRequestApi<People>(volleyQueue,
                        "https://swapi.dev/api/people/?search=$name", peopleList)
                }
                MainSearchTypeEnum.Starship -> {
                    instance.starhipList.clear()

                    addVolleyRequestApi<Starhip>(volleyQueue,
                        "https://swapi.dev/api/starships/?search=$name", starhipList)
                }
                MainSearchTypeEnum.Planet -> {

                }
            }
        }.start()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getFavoriteDataFromLocalDB(){
        if(InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED)GlobalScope.async {
            async {
                favoriteStarhipList = localDatabase.starshipFavoriteDao().getAll().toMutableStateList()
            }.start()

            async {
                favoritePeopleList = localDatabase.peopleFavoriteDao().getAll().toMutableStateList()
            }.start()
        }.start()
    }

    companion object{
        lateinit var instance: Repository
    }
}