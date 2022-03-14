package com.example.telematics_engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.telematics_engine.ui.theme.Telematics_engineTheme

class MainActivity : ComponentActivity() {
    val dbHandler = DbHandler()
    val mainActivityViewModel:MainActivityViewModel= MainActivityViewModel(dbHandler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHandler.read()
        setContent {
            Telematics_engineTheme {
                mainActivityViewModel.MyApp()
            }
        }
    }
}