package khs.onmi.allergies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khs.onmi.allergies.screen.AllergiesRoute
import khs.onmi.navigation.ONMINavRoutes

fun NavGraphBuilder.allergiesNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = ONMINavRoutes.Allergies.MAIN,
        route = ONMINavRoutes.ALLERGIES
    ) {
        composable(
            route = ONMINavRoutes.Allergies.MAIN
        ) {
            AllergiesRoute(
                navController = navController
            )
        }
    }
}
