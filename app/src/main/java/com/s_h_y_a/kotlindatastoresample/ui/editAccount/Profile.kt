package com.s_h_y_a.kotlindatastoresample.ui.editAccount

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.s_h_y_a.kotlindatastore.compose.collectAsMutableState
import com.s_h_y_a.kotlindatastoresample.R
import com.s_h_y_a.kotlindatastoresample.datastore.AccountPref
import com.s_h_y_a.kotlindatastoresample.datastore.MyDataStore
import com.s_h_y_a.kotlindatastoresample.model.Sex

@Composable
fun Profile(back: () -> Unit) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_bar)) },
                navigationIcon = {
                    IconButton(onClick = back, modifier = Modifier) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) {
        Column(Modifier.verticalScroll(scrollState)) {
            Image(
                contentScale = ContentScale.FillWidth,
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Your account icon",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.4f)
            )
            NameTextField()
            SexSelector()
            OldInput()
        }
    }
}

@Composable
fun NameTextField() {
    var savedName by AccountPref.name.collectAsMutableState()
    var tempValue by remember { mutableStateOf("") }
    val isError = savedName != tempValue
    LaunchedEffect(savedName) {
        tempValue = savedName
    }
    Column {
        OutlinedTextField(
            value = tempValue,
            onValueChange = { tempValue = it },
            label = { Text(stringResource(id = R.string.enter_your_name)) },
            isError = isError,
            trailingIcon = {
                SaveIcon {
                    savedName = tempValue
                }
            }
        )
        if (isError) {
            Text(
                text = stringResource(R.string.press_to_save),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun SexSelector() {
    var expanded by remember { mutableStateOf(false) }
    var selected by MyDataStore.sex.collectAsMutableState()
    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(),
                    text = "Sex:${selected.name}"
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
            }
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                enumValues<Sex>()
                    .filter {
                        it != Sex.NotSelected
                    }.forEach {
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                selected = it
                                expanded = false
                                Toast.makeText(context, "Saved!!", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text(it.name, modifier = Modifier
                                .wrapContentWidth()
                            )
                        }
                    }
            }
        }
    }
}

@Composable
fun OldInput() {
    var savedAge by AccountPref.age.collectAsMutableState()
    var tempAge by remember { mutableStateOf(AccountPref.age.defaultValue)}

    LaunchedEffect(savedAge) {
        tempAge = savedAge
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "age")
            NumberPicker(
                value = tempAge,
                onValueChange = { tempAge = it },
                range = 0..150)

            SaveIcon {
                savedAge = tempAge
            }
        }
        if (savedAge != tempAge) {
            Text(
                text = stringResource(R.string.press_to_save),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}