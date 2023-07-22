package com.ntk.testforstarwars.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainSearchTypeEnum() {
    All{
        override var icon: ImageVector = Icons.Default.DateRange
    },
    Person{
        override var icon: ImageVector = Icons.Default.Person
    },
    Starship{
        override var icon: ImageVector = Icons.Default.ShoppingCart
    },
    Planet{
        override var icon: ImageVector = Icons.Default.AccountCircle
    };

    abstract var icon: ImageVector
}