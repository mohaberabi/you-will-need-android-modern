package com.example.multiplebackstack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.multiplebackstack.compose.ChatNavGraph
import com.example.multiplebackstack.compose.HomeNavGraph
import com.example.multiplebackstack.compose.SettingsNavHost
import com.example.multiplebackstack.ui.theme.MultipleBackStackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultipleBackStackTheme {

                val rootNavController = rememberNavController()

                val navBackstackEntry by rootNavController.currentBackStackEntryAsState()
                Scaffold(
                    bottomBar = {


                        NavigationBar {

                            items.forEach { item ->
                                val selected =
                                    item.title.lowercase() == navBackstackEntry?.destination?.route
                                NavigationBarItem(

                                    selected = selected,
                                    onClick = {

                                        /**
                                         * each tab of the navbar item will have it's own backstack
                                         * which means it's stored in the memory if each backstack became more bigger so it takes a lot of memeory
                                         * although what? user in the home tab right now so that means he doesnot need to visualize the other backstatcks rotues
                                         * so what if we stores the backstack out of memory untill we need them and when user
                                         * tabs on a tab it pops all with saving  state [saveState] and make use of only needed backstack
                                         * also [launchSingleTop] make sure to not duplicate routes which is already in top of the backstack
                                         *[restoreState] alwos os to restore the backstack poped
                                         */
                                        rootNavController.navigate(item.title.lowercase()) {
                                            popUpTo(rootNavController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true

                                        }
                                    },
                                    label = { Text(text = item.title) },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.title,
                                            tint = if (selected) MaterialTheme.colorScheme.primary else Color.Gray

                                        )
                                    },
                                )
                            }
                        }
                    }
                ) {


                        padding ->

                    NavHost(
                        navController = rootNavController,
                        startDestination = "home",
                        modifier = Modifier.padding(padding)
                    ) {

                        composable("home") {
                            HomeNavGraph()
                        }
                        composable("chat") {
                            ChatNavGraph()
                        }
                        composable("settings") {
                            SettingsNavHost()
                        }
                    }
                }
            }
        }
    }
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

val items = listOf<BottomNavItem>(
    BottomNavItem("Home", Icons.Filled.Home),
    BottomNavItem("Chat", Icons.Filled.Send),
    BottomNavItem("Settings", Icons.Filled.Settings),
)


