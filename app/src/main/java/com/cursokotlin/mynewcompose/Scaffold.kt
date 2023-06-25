package com.cursokotlin.mynewcompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldExample() {
    val scaffoldState = rememberScaffoldState()
    val courutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppBar(
                onClickIcon = {
                    courutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Has pulsado $it")
                    }
                },
                onClickDrawer = { courutineScope.launch { scaffoldState.drawerState.open() } }) // Abre el menu hamburguesa
        },
        scaffoldState = scaffoldState,
        bottomBar = { MybuttonNav() },
        floatingActionButton = { MyFab() },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = false, // Se coloca arriba de la tab inferior
        drawerContent = { MyDrawer {courutineScope.launch { scaffoldState.drawerState.close() }} },
        drawerGesturesEnabled = false // Esto hace que cuando arrastremos la pantalla salga el menu o no salga
    ) {

    }
}

@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Primer tollbar") },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Search") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Back")
            }
            IconButton(onClick = { onClickIcon("Done") }) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Done")
            }
        }
    )
}

@Composable
fun MybuttonNav() {
    var index by remember { mutableStateOf(0) }
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        BottomNavigationItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home"
            )
        }, label = { Text(text = "Home") })
        BottomNavigationItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star"
            )
        }, label = { Text(text = "Star") })
        BottomNavigationItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = "Face"
            )
        }, label = { Text(text = "Face") })
    }
}

@Composable
fun MyFab() {
    FloatingActionButton(onClick = {}, backgroundColor = Color.Yellow, contentColor = Color.Black) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Primera opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp).clickable { onCloseDrawer() }
        )
        Text(
            text = "Segunda opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp).clickable { onCloseDrawer() }
        )
        Text(
            text = "Tercera opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp).clickable { onCloseDrawer() }
        )
        Text(
            text = "Cuarta opcion", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp).clickable { onCloseDrawer() }
        )
    }
}