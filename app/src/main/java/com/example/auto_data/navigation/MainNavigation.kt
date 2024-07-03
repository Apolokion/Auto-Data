package com.example.auto_data.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.ui.theme.Dimensions.icon_size_normal

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
