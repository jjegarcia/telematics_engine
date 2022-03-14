package com.example.telematics_engine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.telematics_engine.ui.theme.Telematics_engineTheme

class MainActivityViewModel constructor(val dbHandler: DbHandler) : ViewModel() {
    @Composable
    fun MyApp() {
        Cards(dbHandler, accelerometers = listOf("x", "y", "z"))
    }

    @Composable
    private fun Cards(dbHandler: DbHandler, accelerometers: List<String>) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in accelerometers) {
                Card(dbHandler, path = name)
            }
            val accelerometers = dbHandler.accelerometers.collectAsState(initial = 0)
            val xyz by remember { accelerometers }
            Text(xyz.toString())
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
                    onClick = {
                        var numeric = true
                        var num = 0
                        if (!accelerometerValue.isBlank())
                            try {
                                num = accelerometerValue.toInt()
                            } catch (e: NumberFormatException) {
                                numeric = false
                            }
                        if (numeric) dbHandler.write(path, num)
                    }
                ) {
                    Text("Send")
                }
            }
        }
    }

    //    @Preview(showBackground = true, widthDp = 320)
    @Composable
    fun DefaultPreview() {
        Telematics_engineTheme {
            Cards(dbHandler = dbHandler, accelerometers = listOf("x", "y", "z"))
        }
    }
}