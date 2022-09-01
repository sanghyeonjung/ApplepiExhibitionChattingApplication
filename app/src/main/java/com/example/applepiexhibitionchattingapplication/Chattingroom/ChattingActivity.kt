package com.example.applepiexhibitionchattingapplication.Chattingroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.applepiexhibitionchattingapplication.MainActivity
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.ActivityChattingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChattingActivity : AppCompatActivity() {
    lateinit var binding : ActivityChattingBinding
    lateinit var inChattingAdapter : InChattingRecyclerView
    val database : DatabaseReference = Firebase.database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        inChattingAdapter = InChattingRecyclerView()
        binding.chattingRecyclerView.adapter = inChattingAdapter
        binding.chattingRoomNameTextview.text = intent.getStringExtra("chatroomname")
        setData(intent.getStringExtra("chatroomname").toString())

        binding.chattingBackImageButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.chattingSendButton.setOnClickListener {
            setData(intent.getStringExtra("chatroomname").toString())
        }
    }
    private fun setData(chatroom : String){
        database.child("drama").child(chatroom).get().addOnSuccessListener {
            it.value?.let { value ->
                val result = value as HashMap<String,Any>?
                if (result != null) {
                    inChattingAdapter.apply {
                        data.clear()
                    }
                    for(key in result.keys) {
                        inChattingAdapter.data.add(key)
                    }
                }
                inChattingAdapter.notifyDataSetChanged()
            }
        }.addOnFailureListener {
            Log.e("firebase","Error getting data", it)
        }
    }
}