package com.s_h_y_a.kotlindatastoresample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.s_h_y_a.kotlindatastore.compose.collectAsMutableState
import com.s_h_y_a.kotlindatastoresample.datastore.MyDataStore
import com.s_h_y_a.kotlindatastoresample.model.Sex
import com.s_h_y_a.kotlindatastoresample.ui.theme.KotlinDataStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinDataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Box {
        SexSelector()
    }
}

@Composable
fun SexSelector() {
    var expanded by remember { mutableStateOf(false) }
    var selected by MyDataStore.sex.collectAsMutableState()


    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp).align(Alignment.CenterStart).clickable { expanded = !expanded },
            text = selected.name
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { expanded = !expanded }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Open/Close"
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            enumValues<Sex>().forEach {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        selected = it
                        expanded = false
                    }
                ) {
                    Text(it.name, modifier = Modifier
                        .wrapContentWidth())
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinDataStoreTheme {
        Greeting()
    }
}