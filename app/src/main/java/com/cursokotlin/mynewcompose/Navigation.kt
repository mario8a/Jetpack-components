package com.cursokotlin.mynewcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.cursokotlin.mynewcompose.model.Routes

@Composable
fun Screen1(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Pantalla 1",
            Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla2.route) })
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green)
    ) {
        Text(
            text = "Pantalla 2",
            Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla3.route) })
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Pantalla 3",
            Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla4.createRoute(26)) })
    }
}

@Composable
fun Screen4(navController: NavHostController, age: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        Text(
            text = "Tengo $age años",
            Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla5.createRoute("Mario")) })
    }
}

@Composable
fun Screen5(navController: NavHostController, name: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Text(
            text = "Me llamo $name",
            Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.Pantalla1.route) })
    }
}