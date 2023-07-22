package com.ntk.testforstarwars.controllers

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue

class SearchViewController {
    var searchString = mutableStateOf(
        TextFieldValue("")
    )

    private var _onTextChange: (text: TextFieldValue) -> (Unit) = {}

    fun onTextChange(act: (text: TextFieldValue) -> (Unit)): SearchViewController{
        _onTextChange = act

        return this
    }

    fun textChange(text: TextFieldValue) {
        _onTextChange(text)
    }
}