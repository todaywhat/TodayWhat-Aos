package khs.onmi.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khs.onmi.navigation.ONMINavRoutes
import khs.onmi.setting.screen.SettingRoute
import khs.onmi.setting.screen.addwidget.AddWidgetRoute

fun NavGraphBuilder.settingNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = ONMINavRoutes.Setting.MAIN,
        route = ONMINavRoutes.SETTING
    ) {
        composable(
            route = ONMINavRoutes.Setting.MAIN
        ) {
            SettingRoute(
                navController = navController
            )
        }

        composable(
            route = ONMINavRoutes.Setting.ADD_WIDGET
        ) {
            AddWidgetRoute(
                navController = navController
            )
        }
    }
}