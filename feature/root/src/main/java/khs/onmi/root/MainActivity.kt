package khs.onmi.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.enterinformation.navigation.enterInformationNavGraph
import khs.onmi.main.navigation.mainNavGraph
import khs.onmi.navigation.ONMINavRoutes
import khs.onmi.setting.navigation.settingNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkUserAlreadyEnteredInfo { isEntered ->
            setContent {
                ONMITheme { color, _ ->
                    val navController = rememberNavController()
                    val systemUiController = rememberSystemUiController()
                    systemUiController.setSystemBarsColor(color = color.BackgroundMain)

                    NavHost(
                        navController = navController,
                        startDestination = if (isEntered) ONMINavRoutes.MAIN else ONMINavRoutes.ENTERINFOMATION
                    ) {
                        enterInformationNavGraph(navController = navController)

                        mainNavGraph(
                            activity = this@MainActivity,
                            navController = navController
                        )

                        settingNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}