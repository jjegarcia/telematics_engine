package com.example.telematics_engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telematics_engine.ui.theme.Telematics_engineTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp() {
                DisplayScreenContent()
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
fun DisplayScreenContent(
    names: List<String> = List(5) { itemNo ->
        "Hello Android: $itemNo"
    }
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "R.string.accept_cookies") }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {

            NameList(names = names, modifier = Modifier.weight(1f))

            Button(onClick = { /*TODO*/ }) {
                Text("hey")
            }
        }
    }
}

@Composable
private fun NameList(names: List<String>, modifier: Modifier) {
    Column(modifier = modifier) {
        for (name in names) {
            Greeting(name = name)
        }
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
        DisplayScreenContent()
    }
}