package khs.onmi.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khs.onmi.main.screen.MainRoute
import khs.onmi.navigation.ONMINavRoutes

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = ONMINavRoutes.Main.MAIN,
        route = ONMINavRoutes.MAIN
    ) {
        composable(
            route = ONMINavRoutes.Main.MAIN
        ) {
            MainRoute(
                navController = navController
            )
        }
    }
}