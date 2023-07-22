package com.ntk.testforstarwars.view

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ntk.testforstarwars.enums.MainSearchTypeEnum
import com.ntk.testforstarwars.moduls.InAppSetting
import com.ntk.testforstarwars.moduls.Repository
import com.ntk.testforstarwars.view.customView.*
import com.ntk.testforstarwars.viewModels.MainViewModel

@Composable
fun MainScreen(controller: NavHostController, mainViewModel: MainViewModel) {
    if(InAppSetting.instance.IS_ONLINE.value){
        MainScreenUI(mainViewModel)
    }else{
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontSize = 32.sp,
                    text = "Нет соединения",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )

                MultiSplashIndicator()
            }
        }
    }
}

@Composable
fun MainScreenUI(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                SearchRowView(mainViewModel, mainViewModel.searchViewController)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchCategoryView(mainViewModel.getSearchType().name){
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier
                            .fillMaxWidth(),
                        columns = StaggeredGridCells.Adaptive(128.dp),
                        userScrollEnabled = true
                    ){
                        if(
                            mainViewModel.getSearchType() == MainSearchTypeEnum.All
                            || mainViewModel.getSearchType() == MainSearchTypeEnum.Person
                        ){
                            item(span = StaggeredGridItemSpan.FullLine){
                                TitleCategoryView(MainSearchTypeEnum.Person.name)
                            }
                            items(Repository.instance.peopleList){ item ->
                                PeopleItemView(item = item)
                            }
                        }

                        if(
                            mainViewModel.getSearchType() == MainSearchTypeEnum.All
                            || mainViewModel.getSearchType() == MainSearchTypeEnum.Starship
                        ){
                            item(span = StaggeredGridItemSpan.FullLine){
                                TitleCategoryView(MainSearchTypeEnum.Starship.name)
                            }

                            items(Repository.instance.starhipList){item ->
                                StarhipItemView(item = item)
                            }
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun MultiSplashIndicator() {
    val infiniteTransition1 = rememberInfiniteTransition()

    val colorOfProgressBig by infiniteTransition1.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, delayMillis = 100,easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val colorOfProgressMiddle by infiniteTransition1.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(750, delayMillis = 100,easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val colorOfProgressSmall by infiniteTransition1.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(500, delayMillis = 100,easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box {
        Column(
            Modifier.size(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .rotate(180f)
                    .size(100.dp),
                color = colorOfProgressBig,
                strokeWidth = 1.dp
            )
        }

        Column(
            Modifier.size(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .rotate(90f)
                    .size(75.dp),
                color = colorOfProgressMiddle,
                strokeWidth = 1.dp
            )
        }

        Column(
            Modifier.size(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = colorOfProgressSmall,
                strokeWidth = 1.dp
            )
        }
    }
}