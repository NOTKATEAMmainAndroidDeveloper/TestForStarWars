package com.ntk.testforstarwars.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationModel(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val route: String,
    val color: Color
)
