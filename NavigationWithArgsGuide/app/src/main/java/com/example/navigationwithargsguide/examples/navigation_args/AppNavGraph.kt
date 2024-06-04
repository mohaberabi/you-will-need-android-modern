package com.example.navigationwithargsguide.examples.navigation_args

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


/**
 *this type of navigation is quite simpel and straightforward
 *also it can handle the proccess kill
 *the params are auto saved in the [savedStateHandle] by default
 *so even if the process was killed it still has the params states saved in the [savedStateHandle]
 * as android restores the backstack again which already has the args saved in it [<Key,Value> Pairs]
 *but it is complex and hard to save complex object . it 's suitable for primitive types
 * you can extend the parcelable to share or send compelx objects
 * but even this will be complex what if you need to send multiple objects to multiple screen ??
 * use this in very simple form and if the data you tend to get is stateless and immutable
 */
@Composable

fun AppNavGraph(
    navController: NavHostController
) {


    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {

        composable(
            "screen1/{name}",

            ) {
            ScreenOne {
                navController.navigate("screen2/mohabtheloser")
            }
        }
        composable(
            "screen2/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            ),
        ) {
            val name = it.arguments?.getString("name") ?: ""
            ScreenTwo(name)
        }

    }
}
