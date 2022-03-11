package com.example.telematics_engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telematics_engine.ui.theme.Telematics_engineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp() {
                DisplayScreenContent()
//                Greeting(name = "Telematics")
            }
        }
    }
}

@Composable
private fun MyApp(content: @Composable () -> Unit) {
    Telematics_engineTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun DisplayScreenContent() {
    var countState by remember {
        mutableStateOf(0)
    }

    Column {
        Greeting(name = "Android")
        Divider()
        Greeting(name = "Telematics")
        Counter(count = countState, updateCount = { newCount ->
            countState = newCount
        })
        if (countState > 5) Text(text = "Too many clicks")
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "clicks: $count")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
//        DisplayScreenContent()
        Greeting(name = "Telematics")
    }
}