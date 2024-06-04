package com.example.navigationwithargsguide.examples.navigation_viewmodel.nav_graph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.navigationwithargsguide.examples.navigation_viewmodel.screens.AccountInfoScreen
import com.example.navigationwithargsguide.examples.navigation_viewmodel.screens.TermsAndConditionsScreen
import com.example.navigationwithargsguide.examples.navigation_viewmodel.viewmodel.NavigationViewModel


@Composable
fun NavigationViewModelNavGraph() {


    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {

        /**
         *[navigation]  what this navigation does it creates a new nested navigation graph independent
         * now the view-model will stay in the memory and never poped or cleared unless this whole navigation compose is cleared
         * means that any screen sub of that navigation is in the memory this means the view-model remain in the memory as well

         * this approach is complex to test and may introduce bugs if not well written and also hard to understand and coupled
         * use it when you really need to share complex data and needed to be really coupeld together
         */
        navigation(
            startDestination = "account_info",
            route = "onboarding"
        ) {
            composable("account_info") { entry ->
                val viewModel =
                    entry.navigationViewModel<NavigationViewModel>(navController = navController)

                val state by viewModel.state.collectAsState()

                AccountInfoScreen(sharedState = state) {
                    viewModel.updateState()
                    navController.navigate("terms_and_conditions")
                }
            }
            composable("terms_and_conditions") { entry ->
                val viewModel = entry.navigationViewModel<NavigationViewModel>(navController)
                val state by viewModel.state.collectAsState()

                TermsAndConditionsScreen(
                    sharedState = state,
                    onOnboardingFinished = {
                        navController.navigate(route = "other_screen") {
                            popUpTo("onboarding") {
                                inclusive = true
                            }
                        }
                    }
                )
            }

        }
        composable("other_screen") {
            Text(text = "Hello world")
        }
    }
}


/**
 * [reified] this keyowrd lets the compilier to id the type of the Generic Type [T] at runtime
 */


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.navigationViewModel(
    navController: NavHostController
): T {

    val navGraphRoute = destination.parent?.route ?: return viewModel()

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)

}