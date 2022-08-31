package com.example.applepiexhibitionchattingapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repo (val genre : String){
    fun getData() : LiveData<MutableList<String>>{
        val mutableData = MutableLiveData<MutableList<String>>()
        val database = Firebase.database
        val myRef = database.getReference(genre)
        myRef.addValueEventListener(object : ValueEventListener{
            val listData : MutableList<String> = mutableListOf<String>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(chatListSnapshot in snapshot.children){
                        val getData = chatListSnapshot.getValue(String::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }
}