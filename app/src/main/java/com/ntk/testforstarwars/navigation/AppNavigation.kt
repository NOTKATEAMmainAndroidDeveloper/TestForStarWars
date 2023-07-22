package com.ntk.testforstarwars.navigation

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ntk.testforstarwars.models.BottomNavigationModel
import com.ntk.testforstarwars.ui.theme.SelectedBottomMenuColor
import com.ntk.testforstarwars.ui.theme.SelectedSettingBottomMenuColor
import com.ntk.testforstarwars.ui.theme.UnselectedBottomMenuColor
import com.ntk.testforstarwars.view.FavoriteScreen
import com.ntk.testforstarwars.view.MainScreen
import com.ntk.testforstarwars.view.SettingScreen
import com.ntk.testforstarwars.view.customView.BottomNavigationView
import com.ntk.testforstarwars.viewModels.MainViewModel
import com.ntk.testforstarwars.viewModels.ViewModelFactory

@Composable
fun AppNavigation() {
    var bottomNavigationSize = 70.dp

    val controller = rememberNavController()

    val applicationContext = LocalContext.current.applicationContext as Application

    val mainViewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(application = applicationContext)
    )

    NavHost(
        navController = controller,
        startDestination = HostRoute.Main.route,
        modifier = Modifier.padding(bottom = bottomNavigationSize)
    ){
        composable(HostRoute.Main.route){ MainScreen(controller, mainViewModel) }
        composable(HostRoute.Favorite.route){ FavoriteScreen(controller, mainViewModel) }
        composable(HostRoute.Setting.route){ SettingScreen(controller, mainViewModel) }
    }

    var bottomMap = arrayOf(
        BottomNavigationModel(
            1, "Дом", Icons.Default.Home, HostRoute.Main.route, UnselectedBottomMenuColor
        ),

        BottomNavigationModel(
            2, "Избранное", Icons.Default.Favorite, HostRoute.Favorite.route, SelectedBottomMenuColor
        ),

        BottomNavigationModel(
            3, "", Icons.Default.Settings, HostRoute.Setting.route, SelectedSettingBottomMenuColor
        )
    )

    BottomNavigationView(controller, bottomMap, bottomNavigationSize)

}