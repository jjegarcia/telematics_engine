package com.example.telematics_engine

import androidx.compose.runtime.Composable

class MainActivityViewModel(val dbHandler: DbHandler, val composables: Composables = Composables()) {
    val donut = Donut()

    @Composable
    fun MyApp() {
        composables.Composables(
            dbHandler = dbHandler,
            accelerometers = listOf("x", "y", "z"),
            card = Card(),
            donut = Donut()
        )
    }
}

