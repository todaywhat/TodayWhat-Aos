package khs.onmi.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import khs.onmi.enterinformation.navigation.enterInformationNavGraph
import khs.onmi.main.navigation.mainNavGraph
import khs.onmi.navigation.ONMINavRoutes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkUserAlreadyEnteredInfo { isEntered ->
            setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = if (isEntered) ONMINavRoutes.MAIN else ONMINavRoutes.ENTERINFOMATION
                ) {
                    enterInformationNavGraph(navController = navController)

                    mainNavGraph(
                        activity = this@MainActivity,
                        navController = navController
                    )
                }
            }
        }
    }
}