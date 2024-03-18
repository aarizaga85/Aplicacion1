package com.example.aplicacion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicacion1.ui.theme.Aplicacion1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aplicacion1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
//    Box( modifier = Modifier
//            .size(400.dp)) {
//        Text(
//            text = "Text1",
//            color = Color.Blue,
//            fontSize = 20.sp,
//            modifier = Modifier
//                .align(Alignment.BottomStart)
//        )
//        Text(
//            text = "Text2",
//            color = Color.Blue,
//            fontSize = 20.sp,
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//        )
//    }
    
    Column(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "Text1",
                color = Color.Blue,
                fontSize = 20.sp,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = "Text2",
                color = Color.Blue,
                fontSize = 20.sp,
                modifier = Modifier
                    .size(80.dp)
            )
        }
        Text(
            text = "Hola $name!",
            color = Color.Blue,
            fontSize = 40.sp
        )
        Text(
            text = "Otro texto",
            color = Color.Blue,
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Aplicacion1Theme {
        Greeting("Android")
    }
}