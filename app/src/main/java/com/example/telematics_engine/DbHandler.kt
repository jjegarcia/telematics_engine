package com.example.telematics_engine

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DbHandler() {
    val myRef: DatabaseReference by lazy { FirebaseDatabase.getInstance().getReference("test") }

    private val _accelerometers: MutableStateFlow<Accelerometers> = MutableStateFlow(Accelerometers())
    val accelerometers: Flow<Accelerometers> get() = _accelerometers


    fun read() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val read = snapshot.getValue<Accelerometers>()
                Log.w("Acc:", read.toString())
                if (read != null) _accelerometers.value = read
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FB", "Failed to read value.", error.toException())
            }
        })
    }

    fun writeAccelerometers(value: Accelerometers) {
        myRef.setValue(value)
    }

    fun writeAccelerometer(path: String, value: Float) {
        if (value in 0f..10f) myRef.child(path).setValue(value)
    }
}