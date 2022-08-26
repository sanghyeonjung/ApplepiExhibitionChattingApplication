package com.example.applepiexhibitionchattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AddChatRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_chat_room)
        val backBtn = findViewById<ImageButton>(R.id.addBackImageButton)
        backBtn.setOnClickListener {
            startActivity(Intent(this@AddChatRoomActivity,MainActivity::class.java))
        }
    }
}