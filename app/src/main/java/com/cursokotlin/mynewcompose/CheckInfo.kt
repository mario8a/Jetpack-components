package com.cursokotlin.mynewcompose

data class CheckInfo(
    val title: String,
    var selected: Boolean = false,
    val onCheckedChange: (Boolean) -> Unit
) {
}