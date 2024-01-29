package khs.onmi.setup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khs.onmi.navigation.ONMINavRoutes
import khs.onmi.setup.screen.MainRoute

fun NavGraphBuilder.setupNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = ONMINavRoutes.Setup.MAIN,
        route = ONMINavRoutes.SETUP
    ) {
        composable(
            route = ONMINavRoutes.Setup.MAIN
        ) {
            MainRoute(
                navController = navController
            )
        }
    }
}