package com.example.auto_data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auto_data.navigation.BottomNavigationBar
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.navigation.TopBar
import com.example.auto_data.ui.account.AccountScreen
import com.example.auto_data.ui.carModelDescription.CarModelDescriptionScreen
import com.example.auto_data.ui.carModels.CarModelsScreen
import com.example.auto_data.ui.compare.CompareScreen
import com.example.auto_data.ui.main.MainScreen
import com.example.auto_data.ui.news.NewsScreen
import com.example.auto_data.ui.settings.SettingsScreen
import com.example.auto_data.ui.wishList.WishListScreen
import com.example.auto_data.ui.theme.AutoDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoDataTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val selectedTab = remember { mutableStateOf(0) }

                    Scaffold(
                        topBar = { TopBar(navController) },
                        bottomBar = { BottomNavigationBar(navController, selectedTab) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = ScreenObjects.Main.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(ScreenObjects.Main.route) {
                                MainScreen(navController)
                            }
                            composable(ScreenObjects.Compare.route) {
                                CompareScreen()
                            }
                            composable(ScreenObjects.News.route) {
                                NewsScreen()
                            }
                            composable(ScreenObjects.Account.route) {
                                AccountScreen()
                            }
                            composable(ScreenObjects.Settings.route) {
                                SettingsScreen()
                            }
                            composable(ScreenObjects.WishList.route) {
                                WishListScreen()
                            }
                            composable(ScreenObjects.CarModels.route) { backStackEntry ->
                                val carCompany = backStackEntry.arguments?.getString("carCompany")
                                CarModelsScreen(carCompany, navController)
                            }
                            composable(ScreenObjects.CarModelDescription.route) { backStackEntry ->
                                val carModel = backStackEntry.arguments?.getString("carModel")
                                CarModelDescriptionScreen(carModel, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}


