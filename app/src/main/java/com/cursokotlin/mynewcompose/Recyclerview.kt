package com.cursokotlin.mynewcompose

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursokotlin.mynewcompose.model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    var myList = listOf("Mario", "Pepe", "Larla")
    // Solo reciben items
    LazyColumn {
        item { Text(text = "Hola") }
        items(7) {
            Text(text = "Esto son items $it")
        }
        items(myList) {
            Text(text = "Otro text $it")
        }
    }
}

//@Composable
//fun SuperheroGridView() {
//    val context = LocalContext.current
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 6.dp),
//        horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
//        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
//    ) {
//        items(getSuperheroes()) {
//            ItemHero(it) {
//                // Lo que hagamos aqui es lo que se va llamar cuando se de click
//                Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}


@Composable
fun SuperheroSpecialControls() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScore = rememberCoroutineScope()
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheroes()) {
                ItemHero(it) {
                    // Lo que hagamos aqui es lo que se va llamar cuando se de click
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
                }
            }
        }
        val showButton by remember {
            derivedStateOf { // recompone la vista
                rvState.firstVisibleItemIndex > 0
            }
        }

        // rvState.firstVisibleItemScrollOffset // da un valor dependiendo el sitio en que este

        if (showButton) {
            Button(
                onClick = {
                    coroutinesScore.launch {
                        rvState.animateScrollToItem(0)
                    }
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp)
            ) {
                Text(text = "Soy un boton cool")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperheroViewStricky() {
    val context = LocalContext.current
    val superhero: Map<String, List<Superhero>> = getSuperheroes().groupBy { it.publisher }
//    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        superhero.forEach { (publisher, mySuperhero) ->

            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            items(mySuperhero) {
                ItemHero(it) {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}


@Composable
fun SuperheroView() {
    val context = LocalContext.current
//    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) {
            ItemHero(it) {
                // Lo que hagamos aqui es lo que se va llamar cuando se de click
                Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, color = Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(superhero) }) {
        Column() {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "Super hero avatga",
                modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "Peter", "Marvel", R.drawable.spiderman),
        Superhero("Batman", "Bruce", "DC", R.drawable.batman),
        Superhero("Wonder woman", "Mujer maravilla", "DC", R.drawable.wonder_woman),
        Superhero("Wonder woman", "Mujer maravilla", "DC", R.drawable.wonder_woman),
        Superhero("Wonder woman", "Mujer maravilla", "DC", R.drawable.wonder_woman),
        Superhero("Wonder woman", "Mujer maravilla", "DC", R.drawable.wonder_woman),
        Superhero("Wonder woman", "Mujer maravilla", "DC", R.drawable.wonder_woman),
        Superhero("Thor", "Luke cage", "Marvel", R.drawable.thor),
        Superhero("logan", "Lobezno", "Marvel", R.drawable.logan),
        Superhero("Green lantert", "Billy banner", "DC", R.drawable.green_lantern),
        Superhero("Flash", "Barry", "Marvel", R.drawable.flash),
        Superhero("Daredevil", "El ciego", "Marvel", R.drawable.daredevil),
    )
}