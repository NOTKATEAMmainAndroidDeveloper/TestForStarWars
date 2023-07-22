package com.ntk.testforstarwars.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun FavoriteScreen(controller: NavHostController, mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED){
            FavoriteScreenUI()
        }else{
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "В приложении только локальная база(( но в целом не сложно реализовать сохранение в облаке",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FavoriteScreenUI(){
    Text(
        text = "Локальная база",
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White
    )

    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(128.dp),
        userScrollEnabled = true
    ){
        item(span = StaggeredGridItemSpan.FullLine){
            TitleCategoryView(MainSearchTypeEnum.Person.name)
        }
        items(Repository.instance.favoritePeopleList){ item ->
            PeopleItemView(item = item, isInFavorite = true)
        }


        item(span = StaggeredGridItemSpan.FullLine){
            TitleCategoryView(MainSearchTypeEnum.Starship.name)
        }

        items(Repository.instance.favoriteStarhipList){ item ->
            StarhipItemView(item = item, isInFavorite = true)
        }
    }
}