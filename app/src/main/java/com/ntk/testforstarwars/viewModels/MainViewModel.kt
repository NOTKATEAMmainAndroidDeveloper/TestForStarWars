package com.ntk.testforstarwars.viewModels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.ntk.testforstarwars.controllers.SearchViewController
import com.ntk.testforstarwars.enums.MainSearchTypeEnum
import com.ntk.testforstarwars.moduls.InAppSetting
import com.ntk.testforstarwars.moduls.Repository
import com.ntk.testforstarwars.recievers.NetworkRecevier

class MainViewModel(application: Application): AndroidViewModel(application){
    init {
        Log.e("SETTING CHECK", InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED.toString())

        Repository(context = application)
    }

    private var _searchType: MutableState<MainSearchTypeEnum> = mutableStateOf(MainSearchTypeEnum.All)

    var searchViewController = SearchViewController()
        .onTextChange {
            if(it.text.length > 2){
                Repository.instance.searchDataFromApi(it.text, _searchType)
            }
        }

    fun setSearchType(type: MainSearchTypeEnum){
        _searchType.value = type
    }

    fun getSearchType(): MainSearchTypeEnum{
        return _searchType.value
    }
}