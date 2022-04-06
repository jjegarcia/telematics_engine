package com.example.telematics_engine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telematics_engine.Accelerometers
import com.example.telematics_engine.Card
import com.example.telematics_engine.DbHandler
import com.example.telematics_engine.Donut

class Cards() {
    @Composable
    fun Cards(dbHandler: DbHandler, accelerometers: List<String>, card: Card, donut: Donut) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in accelerometers) {
                card.Card(dbHandler, path = name)
            }
            val accelerometers = dbHandler.accelerometers.collectAsState(initial = Accelerometers())
            val xyz by remember { accelerometers }
            Row() {
                donut.PieChart(modifier = Modifier, progressV = xyz.x, color = Color(0xFFbf95d4))
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        val dbHandler = DbHandler()
        Cards(
            dbHandler = dbHandler,
            accelerometers = listOf("x", "y", "z"),
            card = Card(),
            donut = Donut()
        )
    }

}