package com.example.telematics_engine

import android.util.Log
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

class Card() {
    @Composable
    fun Card(dbHandler: DbHandler, path: String) {

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
                        val num: Float = validateNumber(accelerometerValue)
                        if (num != null) dbHandler.writeAccelerometer(path, num)
                    }
                ) {
                    Text("Send")
                }
            }
        }
    }

    private fun validateNumber(accelerometerValue: String): Float {
        var num = 0f
        if (!accelerometerValue.isBlank())
            try {
                num = accelerometerValue.toFloat()
            } catch (e: NumberFormatException) {
                Log.w("Message", "Invalid Number")
            }
        return num
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        Card()
    }

}