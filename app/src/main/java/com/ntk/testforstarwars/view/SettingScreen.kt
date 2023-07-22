package com.ntk.testforstarwars.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ntk.testforstarwars.moduls.InAppSetting
import com.ntk.testforstarwars.moduls.Repository
import com.ntk.testforstarwars.viewModels.MainViewModel

@Composable
fun SettingScreen(controller: NavHostController, mainViewModel: MainViewModel) {
    val checkedLocalBD = remember { mutableStateOf(InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Настройки",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )

        SwitchSettingRow("Использовать локальную базу данных", checkedLocalBD){
            InAppSetting.instance.IS_LOCAL_DATABASE_ENABLED = it

            if(it){
                Repository.instance.favoritePeopleList.clear()
                Repository.instance.favoriteStarhipList.clear()
                Repository.instance.getFavoriteDataFromLocalDB()
            }
        }
    }
}

@Composable
fun SwitchSettingRow(
    title: String,
    checked: MutableState<Boolean>,
    onChanged: (Boolean) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Switch(
            modifier = Modifier.semantics { contentDescription = "Demo" },
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
                onChanged(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))
        
        Text(text = title, color = Color.White)
    }
}