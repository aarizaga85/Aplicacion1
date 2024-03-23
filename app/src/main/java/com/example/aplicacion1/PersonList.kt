package com.example.aplicacion1

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun FormTimer(
    duration: Int,
    onPause: () -> Unit = {},
    onReset: () -> Unit = {},
    onComplete: () -> Unit = {}
 ){
    var timeLeft by remember {
        mutableIntStateOf(duration)
    }

    var isPaused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft>0 && !isPaused){
            delay(1000L)
            timeLeft --
        }
        onComplete()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Time left: ${timeLeft.toString()}",
            modifier = Modifier
                .padding(16.dp),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.padding(end = 16.dp),
            onClick = {
                isPaused = true
                onPause()
            }) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        }
        Button(
            modifier = Modifier
                .padding(end = 16.dp),
            onClick = {
                isPaused = false
                timeLeft = duration
                onReset()
            }) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null
            )
        }

    }
}


@Composable
fun RegistrationForm(
    addPerson: (Person) -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            value = name,
            placeholder = { Text(text = "Enter name") },
            onValueChange = { text ->
                name = text
            })

        TextField(
            value = age,
            placeholder = { Text(text = "Enter age") },
            onValueChange = { text ->
                age = text
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(
            modifier = Modifier
                .padding(end = 16.dp),
            onClick = {
                if (name.isBlank()) {
                    Toast
                        .makeText(
                            context,
                            "Ingresar un nombre",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }

                if (age.isBlank()) {
                    Toast
                        .makeText(
                            context,
                            "Ingresar una edad",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                addPerson(Person(name = name, age = age.toInt()))
                age = ""
                name = ""
            }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Icono de ańadir"
            )
        }
    }
}

@Composable
fun NameListPerson() {


    var persons by remember {
        mutableStateOf(listOf<Person>())
    }


    var isFormEnabled by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FormTimer(
            duration = 20,
            onReset = {
                isFormEnabled = true
            },
            onComplete = {
                isFormEnabled = false
            }
        )

        RegistrationForm(addPerson = { capturedPerson ->
            persons += capturedPerson
        })

        Spacer(modifier = Modifier
            .size(16.dp))

        LazyColumn {
            items(persons) {currentPerson ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )  {
                    Text(
                        text = currentPerson.name,
                        modifier = Modifier
                            .padding(16.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = currentPerson.age.toString(),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                Divider()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreviewPerson(){
    NameListPerson()
}