package com.example.isalmicomposeui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.isalmicomposeui.Colors
import com.example.isalmicomposeui.R
import com.example.isalmicomposeui.hadeth.HadethScreen
import com.example.isalmicomposeui.quran.QuranScreen
import com.example.isalmicomposeui.radio.RadioScreen
import com.example.isalmicomposeui.sebha.SebhaScreen

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image fills the screen
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = "App Background",
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content (Scaffold + NavHost)
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent, // so background shows
            bottomBar = {
                NavigationBar(
                    containerColor = Colors.Primary
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination: NavDestination? = navBackStackEntry?.destination

                    BottomNavItems.forEach { item ->
                        val selected = currentDestination
                            ?.hierarchy
                            ?.any { it.route == item.route } == true

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.label,
                                    modifier = Modifier.size(width = 50.dp , height = 50.dp),
                                    tint = if (selected) Color.White else Color.Black
                                )
                            },
                            label = {

                                if (selected) {
                                    Text(
                                        text = item.label,
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Quran.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(BottomNavItem.Quran.route) { QuranScreen() }
                composable(BottomNavItem.Hadeth.route) { HadethScreen() }
                composable(BottomNavItem.Sebha.route) { SebhaScreen() }
                composable(BottomNavItem.Radio.route) { RadioScreen() }
            }
        }
    }


}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeContentPreview() {

    HomeContent()
}











