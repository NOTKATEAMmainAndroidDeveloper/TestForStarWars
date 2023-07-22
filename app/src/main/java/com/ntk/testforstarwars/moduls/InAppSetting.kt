package com.ntk.testforstarwars.moduls

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class InAppSetting(context: Context) {
    val dataStore = context.getSharedPreferences("settingsDB", Context.MODE_PRIVATE)

    var IS_ONLINE: MutableState<Boolean> = mutableStateOf(true)

    var IS_LOCAL_DATABASE_ENABLED: Boolean = false
        set(value) {
            field = value
            dataStore.edit().putBoolean("IS_LOCAL_DATABASE_ENABLED", value).apply()
        }

    init {
        IS_LOCAL_DATABASE_ENABLED = dataStore.getBoolean("IS_LOCAL_DATABASE_ENABLED", false)
        instance = this
    }

    fun loadSetting(){
        IS_LOCAL_DATABASE_ENABLED = dataStore.getBoolean("IS_LOCAL_DATABASE_ENABLED", false)
    }

    companion object{
        lateinit var instance: InAppSetting
    }
}