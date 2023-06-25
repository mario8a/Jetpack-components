package com.cursokotlin.mynewcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.RangeSlider
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun DetaulfPreviewSlider() {
    AdvanceSlider()
}

@Composable
fun BasicSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by remember { mutableStateOf(0f) }
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun AdvanceSlider() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var sliderPosition by remember { mutableStateOf(0f) }
        var completeValue by remember { mutableStateOf("") }
        Slider(
            value = sliderPosition, onValueChange = { sliderPosition = it },
            valueRange = 0f..10f, steps = 9, enabled = false,
            onValueChangeFinished = { completeValue = sliderPosition.toString() }
        )
        Text(text = completeValue)
    }
}

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun RangeSlider() {
//    var currentRange = remember { mutableStateOf(0f..10f)}
//    RangeSlider(values = currentRange, onValueChange = {currentRange = it})
//}