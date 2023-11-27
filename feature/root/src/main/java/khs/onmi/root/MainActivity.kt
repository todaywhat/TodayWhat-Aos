package khs.onmi.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import khs.onmi.enterinformation.navigation.enterInformationNavGraph
import khs.onmi.navigation.ONMINavRoutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ONMINavRoutes.ENTERINFOMATION
            ) {
                enterInformationNavGraph(navController = navController)
            }
        }
    }
}