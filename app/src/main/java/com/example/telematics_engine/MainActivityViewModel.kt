package com.example.telematics_engine

import androidx.compose.runtime.Composable

class MainActivityViewModel(val dbHandler: DbHandler, val cards: Cards = Cards()) {
    val donut = Donut()

    @Composable
    fun MyApp() {
        cards.Cards(
            dbHandler = dbHandler,
            accelerometers = listOf("x", "y", "z"),
            card = Card(),
            donut = Donut()
        )
    }
}

