package com.example.auto_data.navigation

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auto_data.R
import com.example.auto_data.ui.screen_accounts.AccountScreen
import com.example.auto_data.ui.screen_car_model_description.CarModelDescriptionScreen
import com.example.auto_data.ui.screen_car_models.CarModelsScreen
import com.example.auto_data.ui.screen_compare_cars.CompareScreen
import com.example.auto_data.ui.screen_main.MainScreen
import com.example.auto_data.ui.screen_news.NewsScreen
import com.example.auto_data.ui.screen_settings.SettingsScreen
import com.example.auto_data.ui.theme.Dimensions.icon_size_normal
import com.example.auto_data.ui.screen_wishlist.WishlistScreen

@Composable
fun Main_Navigation() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val selectedTab = remember { mutableIntStateOf(0) }

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
                    WishlistScreen()
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
                IconButton(onClick = { navController.navigate(ScreenObjects.WishList.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.wishlist),
                        contentDescription = "Wishlist",
                        modifier = Modifier.size(icon_size_normal),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(onClick = { navController.navigate(ScreenObjects.Settings.route) }) {
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
                    navController.navigate(ScreenObjects.Main.route) {
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
                    navController.navigate(ScreenObjects.Compare.route) {
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
                    navController.navigate(ScreenObjects.News.route) {
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
                    navController.navigate(ScreenObjects.Account.route) {
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
