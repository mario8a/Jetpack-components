package com.cursokotlin.mynewcompose

import android.graphics.drawable.AdaptiveIconDrawable
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Preview(showBackground = true)
@Composable
fun DetaulfPreviewDialog() {
//    MyDialog()
}

@Composable
fun MyCOnfirmationDialog(show: Boolean, onDissmiss: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { onDissmiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                MyTitleDialog(text = "Phone ringtone", modifier = Modifier.padding(24.dp))
                Divider(Modifier.fillMaxWidth(), Color.LightGray)
                var status by remember { mutableStateOf((""))}
                MyRadioButtonList(name = status, onItemSelected = {status = it})
                Divider(Modifier.fillMaxWidth(), Color.LightGray)
                Row(Modifier.align(Alignment.End).padding(8.dp)) {
                    TextButton(onClick = {}) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {}) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}

@Composable
fun MyCustomDialog(show: Boolean, onDissmiss: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { onDissmiss() }) {
            MyTitleDialog("Set backup account")
            AccountItem("mariotest@test.com", R.drawable.avatar)
            AccountItem("karenromar@test.com", R.drawable.avatar)
            AccountItem("Añadir nueva cuenta", R.drawable.add)
        }
    }
}

@Composable
fun MySimpleCustomDialog(show: Boolean, onDissmiss: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDissmiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
                Text(text = "Esto es un ejemplo")
            }

        }
    }
}

@Composable
fun MyAlertDialog(show: Boolean, onDissmiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = { onDissmiss() },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Descripcion de l dialogo") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "ConfirmButton")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDissmiss() }) {
                    Text(text = "Dismiss button")
                }
            })
    }
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun MyTitleDialog(text: String, modifier: Modifier = Modifier.padding(bottom = 12.dp)) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
    )
}