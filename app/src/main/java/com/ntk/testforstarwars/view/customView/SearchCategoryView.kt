package com.ntk.testforstarwars.view.customView

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchCategoryView(title: String, content: @Composable () -> (Unit)){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {


        Spacer(Modifier.height(8.dp))
        content()
    }

}

@Composable
fun TitleCategoryView(title: String){
    Column(modifier = Modifier
        .padding(start = 8.dp)
        .fillMaxWidth()
    ) {
        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.25f),
            color = Color.White,
            thickness = 1.dp
        )
    }
}