package com.cursokotlin.mynewcompose

import android.widget.Space
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun ColorAnimationSimple() {

    var secondColor by rememberSaveable {
        mutableStateOf(false)
    }

    var showBox by rememberSaveable {
        mutableStateOf(false)
    }

    val realSecondColor by animateColorAsState(
        targetValue = if (secondColor) Color.Red else Color.Yellow,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = { showBox = false }
    )

    if (showBox) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(realSecondColor)
                .clickable { secondColor = !secondColor }
        )
    }
}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable {
        mutableStateOf(true)
    }
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = {}
    )
    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable { smallSize = !smallSize })
}

@Composable
fun VisibilityAnimation() {

    var isVisible by remember {
        mutableStateOf(true)
    }



    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar/ocultar")
        }
        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun CrossfadeAnimation() {
    var myComponentType: ComponentType by remember {
        mutableStateOf(ComponentType.Text)
    }
    Column(Modifier.fillMaxSize()) {

        Button(onClick = { myComponentTypeRandom() }) {
            Text(text = "Cambiar componente")
        }
        
        Crossfade(targetState = myComponentType) {
            when (it) {
                ComponentType.Image -> Icon(Icons.Default.Send, contentDescription = "")
                ComponentType.Text -> Text(text = "Soy un componente")
                ComponentType.Box -> Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Red)
                )
                ComponentType.Error -> Text(text = "Error")
            }
        }
    }
}

fun myComponentTypeRandom():ComponentType {
    return when(Random.nextInt(from = 0, until = 3)) {
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> ComponentType.Error
    }
}

enum class ComponentType() {
    Image, Text, Box, Error
}






















