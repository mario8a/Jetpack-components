package com.cursokotlin.mynewcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cursokotlin.mynewcompose.model.Routes
import com.cursokotlin.mynewcompose.ui.theme.MyNewComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNewComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    ScaffoldExample()
//                    val myOptions = getOptions(listOf("Aris", "Mario", "Ejemplo"))
//                    myTriStatusCheckBox()
//                    myOptions.forEach {
//                        MyCheckboxWithText2(it)
//                    }
//                    var selected by remember {
//                        mutableStateOf("Ejemplo 1")
//                    }
//                    MyRadioButtonList(selected) { selected = it }
//                    MyCheckboxWithText()
//                    var myTextOnField by rememberSaveable { mutableStateOf("") }
//                    Column() {
//                        MyTextField(myTextOnField) { myTextOnField = it }
//                    }

//                    var show by remember {
//                        mutableStateOf(false)
//                    }
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        Button(onClick = { show = true }) {
//                            Text(text = "Mostrar dialogo")
//                        }
//                        MyCustomDialog(
//                            show = show,
//                            onDissmiss = { show = false },
//                        )
//                    }

//                    val navigationController = rememberNavController()
//                    NavHost(
//                        navController = navigationController,
//                        startDestination = Routes.Pantalla1.route
//                    ) {
//                        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
//                        composable(Routes.Pantalla2.route) { Screen2(navigationController) }
//                        composable(Routes.Pantalla3.route) { Screen3(navigationController) }
//                        composable(
//                            Routes.Pantalla4.route,
//                            arguments = listOf(navArgument("age") { type = NavType.IntType })
//                        ) { backStackEntry ->
//
//                            Screen4(
//                                navigationController,
////                                backStackEntry.arguments?.getString("name").orEmpty()
//                                backStackEntry.arguments?.getInt("age") ?: 0
//                            )
//                        }
//                        composable(
//                            Routes.Pantalla5.route,
//                            arguments = listOf(navArgument("name") { defaultValue = "Pepe" })
//                        ) { backStackEntry ->
//
//                            Screen5(
//                                navigationController,
////                                backStackEntry.arguments?.getString("name").orEmpty()
//                                backStackEntry.arguments?.getString("name")
//                            )
//                        }
//                    }
                    ColorAnimationSimple()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetaulfPreview() {
    MyDropDown()
}

@Composable
fun MyDropDown() {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember {
        mutableStateOf(false)
    }
    val desserts = listOf("Helado", "Fruta", "Natilla", "Chilaquiles", "Cafe")

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedText = dessert
                }) {
                    Text(text = dessert)
                }
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), color = Color.Red
    )// Es una linea :D
}

@Composable
fun MyBadgeBox() {
    BadgedBox(
        modifier = Modifier.padding(20.dp),
        badge = {
            Badge(
                content = {
                    Text(modifier = Modifier.padding(2.dp), text = "10")
                },
                backgroundColor = Color.Black,
                contentColor = Color.White,
            )
        },
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "l",
            tint = Color.Blue
        )
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.Cyan,
        contentColor = Color.Red,
        border = BorderStroke(5.dp, Color.Green)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Ejemplo")
            Text(text = "Ejemplo")
            Text(text = "Ejemplo")
        }
    }
}


// Radio buttons
@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = false, onClick = {}, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Blue,
                disabledColor = Color.Green
            )
        )
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 1", onClick = { onItemSelected("Ejemplo 1") })
            Text(text = "Ejemplo 1")
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 2", onClick = { onItemSelected("Ejemplo ") })
            Text(text = "Ejemplo 2")
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 3", onClick = { onItemSelected("Ejemplo 3") })
            Text(text = "Ejemplo 3")
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Ejemplo 4", onClick = { onItemSelected("Ejemplo 4") })
            Text(text = "Ejemplo 4")
        }
    }

}

@Composable
fun myTriStatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> {
                ToggleableState.Off
                // ¨Podemos hacer mas cosas
                // Cuando se marque que chequee a todos sus hijos
            }
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.Off
        }
    })
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { status = it }
        )
    }

}

@Composable
fun MyCheckboxWithText2(checkInfo: CheckInfo) {

    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

@Composable
fun MyCheckboxWithText() {
    var state by rememberSaveable { mutableStateOf(true) }

    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

//CheckBox
@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(true) }
    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Blue
        )
    )
}

// Switch
@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }
    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = false,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            uncheckedTrackColor = Color.Magenta,
            checkedTrackColor = Color.Cyan,
            checkedTrackAlpha = 0.5f,
            uncheckedTrackAlpha = 0.3f,
            disabledCheckedThumbColor = Color.Yellow
        )
    )

}

// PROGRESSBAR
@Composable
fun MyProgressAdvance() {

    var progressStatus by rememberSaveable { mutableStateOf(0f) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progressStatus)
        LinearProgressIndicator(progress = progressStatus)
        Row(Modifier.fillMaxSize()) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Decrementar")
            }
        }
    }
}

@Composable
fun MyProgressBar() {

    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red)
            LinearProgressIndicator(Modifier.padding(top = 32.dp))
        }
    }

    Button(onClick = { showLoading = !showLoading }) {
        Text(text = "show loading")
    }
}

// Imagenes

@Composable
fun MyImageIcon() {
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "icono", tint = Color.Cyan)
}

@Composable
fun MyImageCircular() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
//        modifier = Modifier.clip(RoundedCornerShape(25f)) // clip permite recortar imagenes
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape) // Circulo perfecto
    )
}

@Composable
fun MyImageComp() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
    )
}

// BOTONES

@Composable
fun MyButtonExample() {

    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Blue
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Hola ")
        }

        OutlinedButton(
            onClick = { enabled = false },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.Blue
            ),
        ) {
            Text(text = "Hola")
        }
        TextButton(onClick = {}) {
            Text(text = "Hola")
        }
    }
}

// ---------
@Composable
fun MyTextFieldPutlined() {
    var myText by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(14.dp),
        label = { Text(text = "Hola") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Red
        )
    )
}

@Composable
fun MyTexttFieldAdvance() {
    var myText by rememberSaveable { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "*")
            } else {
                it
            }
        },
        label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es lo mas basico")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Bold)
        Text(text = "Esto es un ejemplo", fontFamily = FontFamily.Cursive)
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Esto es un ejemplo",
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }

}

@Composable
fun MyStatExample() {

    // Usamos remeber para que el componente no se renderiza cada vez que se cambie
    // el valor
    // Usamos mutableSetOf para cambiar una variable ya que no podemos cambiar solo asi nada mas
    var counter by rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1 }) {
            Text(text = "Pulsar")
        }
        Text(text = "He sido pulsado $counter veces")
    }
}

@Composable
fun MyComplexLayout() {
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan), contentAlignment = Alignment.Center
        ) {
            Text(text = "Ejemplo nuevo")
        }
        MySpacer(size = 30)
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Red), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo nuevo")
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Green), contentAlignment = Alignment.Center
            ) {
                Text(text = "Hola soy el Mario :D")
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Magenta), contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Otro ejemplo")

        }
    }
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun MyRow() {
//    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
//        Text(text = "Ejemplo 1")
//        Text(text = "Ejemplo 1")
//        Text(text = "Ejemplo 1")
//    }
    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        Text(text = "Ejemplo 1", modifier = Modifier.weight(1f))
        Text(text = "Ejemplo 1", modifier = Modifier.weight(1f))
        Text(text = "Ejemplo 1", modifier = Modifier.weight(1f))
    }
}

@Composable
fun MyColumn() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Ejemplp", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = "Ejemplp", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = "Ejemplp", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}

@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(color = Color.Cyan)
                .width(200.dp)
                .height(200.dp)
        ) {
            Text(text = "Esto es un ejemplo")
        }
    }
}
