package com.example.auto_data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auto_data.navigation.Screen
import com.example.auto_data.screens.AccountScreen
import com.example.auto_data.screens.CarModelDescriptionScreen
import com.example.auto_data.screens.CarModelsScreen
import com.example.auto_data.screens.CompareScreen
import com.example.auto_data.screens.MainScreen
import com.example.auto_data.screens.NewsScreen
import com.example.auto_data.screens.SettingsScreen
import com.example.auto_data.screens.WishListScreen
import com.example.auto_data.ui.theme.AutoDataTheme
import com.example.auto_data.ui.theme.icon_size_normal

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
                            startDestination = Screen.Main.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Main.route) {
                                MainScreen(navController)
                            }
                            composable(Screen.Compare.route) {
                                CompareScreen()
                            }
                            composable(Screen.News.route) {
                                NewsScreen()
                            }
                            composable(Screen.Account.route) {
                                AccountScreen()
                            }
                            composable(Screen.Settings.route) {
                                SettingsScreen()
                            }
                            composable(Screen.WishList.route) {
                                WishListScreen()
                            }
                            composable(Screen.CarModels.route) { backStackEntry ->
                                val carCompany = backStackEntry.arguments?.getString("carCompany")
                                CarModelsScreen(carCompany, navController)
                            }
                            composable(Screen.CarModelDescription.route) { backStackEntry ->
                                val carModel = backStackEntry.arguments?.getString("carModel")
                                CarModelDescriptionScreen(carModel, navController)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun TopBar(navController: NavHostController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                // Your navigation icon here
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.auto_data_name),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .height(42.dp)
                        .padding(top = 8.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row {
                    IconButton(onClick = { navController.navigate(Screen.WishList.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.wishlist),
                            contentDescription = "Wishlist",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Settings",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController, selectedTab: MutableState<Int>) {
        Box(modifier = Modifier.height(56.dp)) {
            NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Home",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    selected = selectedTab.value == 0,
                    onClick = {
                        selectedTab.value = 0
                        navController.navigate(Screen.Main.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.car),
                            contentDescription = "Compare",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    selected = selectedTab.value == 3,
                    onClick = {
                        selectedTab.value = 3
                        navController.navigate(Screen.Compare.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.newspaper),
                            contentDescription = "News",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    },
                    selected = selectedTab.value == 2,
                    onClick = {
                        selectedTab.value = 2
                        navController.navigate(Screen.News.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Account",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    selected = selectedTab.value == 1,
                    onClick = {
                        selectedTab.value = 1
                        navController.navigate(Screen.Account.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
    }
}
