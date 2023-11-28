package khs.onmi.enterinformation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khs.onmi.enterinformation.screen.MainScreen
import khs.onmi.navigation.ONMINavRoutes

fun NavGraphBuilder.enterInformationNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = ONMINavRoutes.EnterInformation.MAIN,
        route = ONMINavRoutes.ENTERINFOMATION
    ) {
        composable(
            route = ONMINavRoutes.EnterInformation.MAIN
        ) {
            MainScreen(navController = navController)
        }
    }
}