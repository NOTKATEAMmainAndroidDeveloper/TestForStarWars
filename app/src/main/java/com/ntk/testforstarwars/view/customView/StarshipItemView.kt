package com.ntk.testforstarwars.view.customView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntk.testforstarwars.models.People
import com.ntk.testforstarwars.models.Starhip
import com.ntk.testforstarwars.moduls.Repository
import com.ntk.testforstarwars.ui.theme.GreyBlackMinus

@Composable
fun StarhipItemView(item: Starhip, isInFavorite: Boolean = false){
    Card(
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = GreyBlackMinus
        )
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
        ) {
            Text(
                text = item.name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Модель: " + item.model,
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Завод: " + item.manufacturer,
                color = Color.White,
                fontSize = 14.sp
            )

            Text(
                text = "Пассажиров: " + item.passengers,
                color = Color.White,
                fontSize = 14.sp
            )

            FavoriteButtonRowView(item = item, isInFavorite, item.name)
        }
    }
}