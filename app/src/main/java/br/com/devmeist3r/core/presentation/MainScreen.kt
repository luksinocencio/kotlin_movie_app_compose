package br.com.devmeist3r.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.devmeist3r.core.presentation.navigation.BottomNavigationBar
import br.com.devmeist3r.core.presentation.navigation.DetailScreenNav
import br.com.devmeist3r.core.presentation.navigation.NavigationGraph
import br.com.devmeist3r.core.presentation.navigation.currentRoute

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) != DetailScreenNav.DetailScreen.route) {
                BottomNavigationBar(navController = navController)
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navHostController = navController)
            }
        }
    )
}