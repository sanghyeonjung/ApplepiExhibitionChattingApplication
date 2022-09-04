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
import java.util.*
import kotlin.collections.HashMap

class ChattingActivity : AppCompatActivity() {
    lateinit var binding : ActivityChattingBinding
    lateinit var inChattingAdapter : InChattingRecyclerView
    lateinit var database : DatabaseReference
    lateinit var baseRef : DatabaseReference
    var userName :String = ""
    var cnt : Int =0
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        database = FirebaseDatabase.getInstance().getReference()
        val nowChatroom = intent.getStringExtra("chatroomname").toString()
        baseRef = database.child(UtilCode.currentGenre).child(nowChatroom)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        inChattingAdapter = InChattingRecyclerView()
        binding.chattingRecyclerView.adapter = inChattingAdapter
        binding.chattingRoomNameTextview.text = intent.getStringExtra("chatroomname")
        val chatEdittext = binding.chattingChatEdittext
        setData()
        db.collection("user").document(UtilCode.getInstance().uid!!).get()
            .addOnSuccessListener { it->
                if(it != null)
                {
                    userName = it.data!!.get("id").toString()
                    Log.e("set username","$userName")
                }
                else
                {
                    Log.e("error","error")
                }
            }.addOnFailureListener {
                Log.e("error","username get fail")
            }
        baseRef.child("cnt").get().addOnSuccessListener {
            it.value?.let { value ->
                val str : String = value.toString()
                cnt = str.toInt()
                Log.e("set cnt","$cnt")
            }
        }.addOnFailureListener {
            Log.e("error","cnt get fail")
        }

        binding.chattingBackImageButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.chattingSendButton.setOnClickListener {
            Log.d("cnt.setonclick","$cnt")
            baseRef.child("chat").child("$cnt").setValue("${userName}:${chatEdittext.text}")
            baseRef.child("cnt").setValue("${cnt+1}")
            cnt++
            setData()
        }

    }
    private fun setData()
    {
        baseRef.child("chat").get().addOnSuccessListener {
            it.value?.let { value ->
                Log.d("chatting value","$value")
                val result = value as HashMap<String,Any>?
                if (result != null) {
                    inChattingAdapter.apply {
                        data.clear()
                    }
                    for(v in result.values) {
                        Log.d("value","${result.values}")
                        inChattingAdapter.apply {
                            data.add(v.toString())
                        }
                    }
                    inChattingAdapter.apply {
                        notifyDataSetChanged()
                        Log.e("datas","$data")
                    }
                }
            }
        }.addOnFailureListener {
            Log.e("firebase","Error getting data", it)
        }
    }
}