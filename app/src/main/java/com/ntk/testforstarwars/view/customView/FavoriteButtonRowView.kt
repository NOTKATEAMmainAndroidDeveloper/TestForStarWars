package com.ntk.testforstarwars.view.customView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.ntk.testforstarwars.moduls.Repository

@Composable
inline fun <reified T> FavoriteButtonRowView(item: T, isFavorite: Boolean, name: String){
    val isInFavorite = mutableStateOf(isFavorite)

    isInFavorite.value = Repository.instance.isItemInFavorite(name)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                color = Color.White
            )
        }
        IconButton(onClick = {
            if(!isInFavorite.value){
                Repository.instance.saveFavorite<T>(item)

                isInFavorite.value = true
            }else{
                Repository.instance.deleteFavorite<T>(item)

                isInFavorite.value = false
            }
        }) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = if(!isInFavorite.value) Color.White else Color.Red
            )
        }
    }
}