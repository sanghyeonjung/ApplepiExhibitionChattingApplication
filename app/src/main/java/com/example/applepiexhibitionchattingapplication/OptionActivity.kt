package com.example.applepiexhibitionchattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import com.example.applepiexhibitionchattingapplication.UtilCode.auth
import com.example.applepiexhibitionchattingapplication.UtilCode.uid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
        val optionBackBtn = findViewById<ImageButton>(R.id.optionBackImageButton)
        val myNameEditText = findViewById<EditText>(R.id.optionMyNameSetEdittext)
        val db = FirebaseFirestore.getInstance()
        db.collection("user")
            .document(UtilCode.getInstance().uid!!)
            .get()
            .addOnSuccessListener { it->
                myNameEditText.setText(it.data!!.get("id").toString())
            }
        optionBackBtn.setOnClickListener {
            startActivity(Intent(this@OptionActivity,MainActivity::class.java))
        }
    }
}