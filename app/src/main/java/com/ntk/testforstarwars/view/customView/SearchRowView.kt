package com.ntk.testforstarwars.view.customView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ntk.testforstarwars.controllers.SearchViewController
import com.ntk.testforstarwars.enums.MainSearchTypeEnum
import com.ntk.testforstarwars.ui.theme.BackgroundMainSearchDropDown
import com.ntk.testforstarwars.ui.theme.ContainerMainSearchDropDown
import com.ntk.testforstarwars.ui.theme.SelectedBottomMenuColor
import com.ntk.testforstarwars.viewModels.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchRowView(mainViewModel: MainViewModel, searchViewController: SearchViewController) {
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(20.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Spacer(Modifier.width(5.dp))

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                value = searchViewController.searchString.value,
                onValueChange = {
                    searchViewController.searchString.value = it
                    searchViewController.textChange(it)
                },
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 21.sp,
                    color = Color.White,

                )
            ){
                it()
                if (searchViewController.searchString.value.text.isEmpty()) {
                    Text(
                        text = "Начните поиск!",
                        fontSize = 18.sp,
                        color = Color.LightGray
                    )
                }

            }

            Icon(
                modifier = Modifier
                    .clickable {
                        expanded = true
                    },
                imageVector = mainViewModel.getSearchType().icon,
                tint = SelectedBottomMenuColor,
                contentDescription = null
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(100.dp)
                    .background(BackgroundMainSearchDropDown)
            ) {

                MainSearchTypeEnum.values().forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            mainViewModel.setSearchType(item)
                            expanded = false
                        },
                        text = {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = ContainerMainSearchDropDown
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(2.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = null,
                                        tint = SelectedBottomMenuColor
                                    )

                                    Text(item.name, color = Color.White)
                                }
                            }
                        }
                    )
                }
            }


            Spacer(Modifier.width(5.dp))

        }
    }
}