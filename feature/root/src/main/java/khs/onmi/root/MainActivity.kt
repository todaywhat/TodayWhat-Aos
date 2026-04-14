package khs.onmi.root

import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import khs.onmi.core.designsystem.theme.ONMITheme
import khs.onmi.enterinformation.navigation.enterInformationNavGraph
import khs.onmi.main.navigation.mainNavGraph
import khs.onmi.navigation.ONMINavRoutes
import khs.onmi.allergies.navigation.allergiesNavGraph
import khs.onmi.setting.navigation.settingNavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val isDebuggable = applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

        viewModel.checkUserAlreadyEnteredInfo { isEntered ->
            setContent {
                ONMITheme { color, _ ->
                    val navController = rememberNavController()

                    Box(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            navController = navController,
                            startDestination = if (isEntered) ONMINavRoutes.MAIN else ONMINavRoutes.ENTERINFOMATION
                        ) {
                            enterInformationNavGraph(navController = navController)

                            mainNavGraph(navController = navController)

                            settingNavGraph(navController = navController)

                            allergiesNavGraph(navController = navController)
                        }

                        if (isDebuggable) {
                            DebugRibbon(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .zIndex(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

private val DebugRibbonBackgroundColor = Color(0xCCD32F2F)

@Composable
private fun DebugRibbon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(DebugRibbonBackgroundColor)
            .statusBarsPadding()
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "DEBUG",
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}