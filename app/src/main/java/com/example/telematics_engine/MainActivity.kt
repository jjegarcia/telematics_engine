package com.example.telematics_engine

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.telematics_engine.ui.theme.Telematics_engineTheme

class MainActivity : ComponentActivity(), SensorEventListener {
    val dbHandler = DbHandler()
    val mainActivityViewModel: MainActivityViewModel = MainActivityViewModel(dbHandler)
    val timerHandler=TimerHandler()
    var sensorManager: SensorManager?= null
    var sensor: Sensor?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if (sensorManager !=null) {
            sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager?.registerListener(this,sensor,40000000)
        }


        dbHandler.read()
        timerHandler.setUp(1000000,1000)
        timerHandler.timer.start()
        setContent {
            Telematics_engineTheme {
                mainActivityViewModel.MyApp()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event !=null) {
            Log.i("sensor:", event.values.toString())
             dbHandler.writeAccelerometers(Accelerometers(event.values[0], event.values[1], event.values[2]))
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        TODO("Not yet implemented")
    }


}