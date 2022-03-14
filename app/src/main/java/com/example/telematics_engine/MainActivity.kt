package com.example.telematics_engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telematics_engine.ui.theme.Telematics_engineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbHandler = DbHandler()
        dbHandler.read("X")
        dbHandler.read("Y")
        dbHandler.read("Z")
        setContent {
            Telematics_engineTheme {
                MyApp(dbHandler)
            }
        }
    }
}

@Composable
fun MyApp(dbHandler: DbHandler) {
    Cards(dbHandler, accelerometers = listOf("X", "Y", "Z"))
}

@Composable
private fun Cards(dbHandler: DbHandler, accelerometers: List<String>) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in accelerometers) {
            Card(dbHandler, path = name)
        }
    }
}

@Composable
private fun Card(dbHandler: DbHandler, path: String) {

    var accelerometerValue by remember {
        mutableStateOf("")
    }
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 0.dp)
            ) {
                TextField(
                    value = accelerometerValue,
                    onValueChange = { accelerometerValue = it },
                    label = { Text(text = path) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            OutlinedButton(
                onClick = { dbHandler.write(path, accelerometerValue.toInt()) }
            ) {
                Text("Send")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    Telematics_engineTheme {
//        Cards(dbHandler = dbHandler, accelerometers = listOf("X", "Y", "Z"))
    }
}