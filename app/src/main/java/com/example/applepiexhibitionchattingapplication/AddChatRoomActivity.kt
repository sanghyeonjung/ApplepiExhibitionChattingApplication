package com.example.applepiexhibitionchattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddChatRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_chat_room)

        val database :DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val backBtn = findViewById<ImageButton>(R.id.addBackImageButton)
        val confirmBtn = findViewById<Button>(R.id.addConfirmButton)
        val chatEdittext = findViewById<EditText>(R.id.addChatNameEdittext)
        val radioGroup = findViewById<RadioGroup>(R.id.addRadioGroup)


        backBtn.setOnClickListener {
            startActivity(Intent(this@AddChatRoomActivity,MainActivity::class.java))
        }

        confirmBtn.setOnClickListener{
            if(chatEdittext.text.toString().replace(" ","").equals(""))
            {
                Toast.makeText(this,"채팅방 이름을 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
            else
            {
                when(radioGroup.checkedRadioButtonId){
                    R.id.addWebtoonRadioButton -> database.child("webtoon").push().setValue(chatEdittext.text.toString())
                    R.id.addMovieRadioButton -> database.child("movie").push().setValue(chatEdittext.text.toString())
                    R.id.addDramaRadioButton -> database.child("drama").push().setValue(chatEdittext.text.toString())
                    else -> Toast.makeText(this,"장르를 선택해주세요.",Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this@AddChatRoomActivity,MainActivity::class.java))
            }
        }
    }
}