package com.ntk.testforstarwars.view.customView

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ntk.testforstarwars.models.BottomNavigationModel
import com.ntk.testforstarwars.ui.theme.BackgroundBottomMenuColor
import com.ntk.testforstarwars.ui.theme.SelectedBottomMenuColor
import com.ntk.testforstarwars.ui.theme.UnselectedBottomMenuColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationView(
    controller: NavHostController,
    bottomMap: Array<BottomNavigationModel>,
    bottomNavigationSize: Dp
) {

    val selected = remember {
        mutableStateOf(bottomMap[0].id)
    }

    val selectedColor = remember {
        mutableStateOf(bottomMap[0].color)
    }

    val bgColor by animateColorAsState(
        selectedColor.value,
        animationSpec = tween(1000, easing = LinearEasing)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f)
                .height(bottomNavigationSize)
                .padding(bottom = 10.dp)
        ){
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding((bottomNavigationSize.value / 10).dp)
                    .shadow(8.dp, spotColor = bgColor, shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundBottomMenuColor
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 21.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    for(path in bottomMap){
                        BottomNavigationButtonView(controller, path, selected, selectedColor)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationButtonView(
    controller: NavHostController,
    path: BottomNavigationModel,
    selected: MutableState<Int>,
    selectedColor: MutableState<Color>
) {
    Button(

        onClick = {
            if(path.id != selected.value){
                selected.value = path.id
                selectedColor.value = path.color
                controller.navigate(path.route)
            }
        },

        colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        Icon(
            path.icon,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
            tint = if(selected.value == path.id) path.color else UnselectedBottomMenuColor
        )
        Spacer(Modifier.width(2.dp))
        Text(path.name)

    }
}