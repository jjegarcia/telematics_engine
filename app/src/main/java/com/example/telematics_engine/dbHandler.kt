package com.example.telematics_engine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class DbHandler() {
    val myRef: DatabaseReference by lazy { FirebaseDatabase.getInstance().getReference("test") }

    private val _accelerometers: MutableLiveData<Accelerometers> = MutableLiveData()
    val accelerometers: LiveData<Accelerometers> = _accelerometers


    fun read() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.w("Acc:",snapshot.getValue<Accelerometers>().toString())
                //_accelerometers.postValue(snapshot.getValue<Accelerometers>())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FB", "Failed to read value.", error.toException())
            }
        })
    }

    fun write(path: String, value: Int) {
        if(value in 0..1000) myRef.child(path).setValue(value)
    }
}