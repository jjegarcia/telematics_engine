package com.example.telematics_engine

import android.util.Log
import com.google.firebase.database.*

class DbHandler() {
    //Set up reference to DB
    val myRef: DatabaseReference by lazy {   FirebaseDatabase.getInstance().getReference("test")}


    fun read(path: String) {
        // Read from the database using reference to field above
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.w("DB read- $path",snapshot.child(path).getValue().toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FB", "Failed to read value.", error.toException())
            }
        })
    }
     fun write(path: String, value: Any?){
        myRef.child(path).setValue(value)
    }
}