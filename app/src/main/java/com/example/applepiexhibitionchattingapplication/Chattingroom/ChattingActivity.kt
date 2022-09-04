package com.example.applepiexhibitionchattingapplication.Chattingroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.applepiexhibitionchattingapplication.MainActivity
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.UtilCode
import com.example.applepiexhibitionchattingapplication.databinding.ActivityChattingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChattingActivity : AppCompatActivity() {
    lateinit var binding : ActivityChattingBinding
    lateinit var inChattingAdapter : InChattingRecyclerView
    lateinit var database : DatabaseReference
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        database = FirebaseDatabase.getInstance().getReference()
        val nowChatroom = intent.getStringExtra("chatroomname").toString()
        Log.e("nowChatroom","$nowChatroom")
        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        inChattingAdapter = InChattingRecyclerView()
        binding.chattingRecyclerView.adapter = inChattingAdapter
        binding.chattingRoomNameTextview.text = intent.getStringExtra("chatroomname")



        val chatEdittext = binding.chattingChatEdittext
        setData(nowChatroom)

        binding.chattingBackImageButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        var cnt : Int = 0
        binding.chattingSendButton.setOnClickListener {
            database.child(UtilCode.currentGenre)
                .child(nowChatroom)
                .child("cnt")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        Log.d("type","${snapshot.value?.javaClass?.name}")
                        val str : String = snapshot.value as String
                        cnt = str.toInt()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            Log.d("cnt","$cnt")
            var userName :String = ""
            db.collection("user")
                .document(UtilCode.getInstance().uid!!)
                .get()
                .addOnSuccessListener { it->
                    userName = it.data!!.get("id").toString()
                    Log.e("userNameout","$userName")
                    database.child(UtilCode.currentGenre).child(nowChatroom).child("chat").child(cnt.toString()).setValue("${it.data!!.get("id").toString()}:${chatEdittext.text}")
                    database.child(UtilCode.currentGenre).child(nowChatroom).child("cnt").setValue((cnt+1).toString())
                }
            setData(nowChatroom)
        }
    }
    private fun setData(chatroom : String){
        database.child(UtilCode.currentGenre).child(chatroom)
            .child("chat").get().addOnSuccessListener {
            it.value?.let { value ->
                Log.d("chatting value","$value")
                val result = value as HashMap<String,Any>?
                if (result != null) {
                    inChattingAdapter.apply {
                        data.clear()
                        notifyDataSetChanged()
                    }
                    for(value in result.values) {
                        inChattingAdapter.apply {
                            data.add(value as String)
                        }
                    }
                }
                inChattingAdapter.notifyDataSetChanged()
            }
        }.addOnFailureListener {
            Log.e("firebase","Error getting data", it)
        }
    }
}