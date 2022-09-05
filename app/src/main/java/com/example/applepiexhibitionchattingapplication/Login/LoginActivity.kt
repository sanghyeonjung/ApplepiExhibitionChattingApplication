package com.example.applepiexhibitionchattingapplication.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.applepiexhibitionchattingapplication.MainActivity
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.UtilCode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val emailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }
        val passwordEditText: EditText by lazy { findViewById<EditText>(R.id.passwordEditText) }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)
            val signInBtn = findViewById<Button>(R.id.signInButton)
            val signUpBtn = findViewById<Button>(R.id.signUpButton)
            signInBtn.setOnClickListener {
                if(!emailEditText.text.isEmpty()) {
                    if(!passwordEditText.text.isEmpty())
                    {
                        loginEmail()
                    }
                }
                else
                {
                    Toast.makeText(
                        this,
                        "이메일과 비밀번호를 작성해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            signUpBtn.setOnClickListener {
                if(!emailEditText.text.isEmpty()) {
                    if(!passwordEditText.text.isEmpty())
                    {
                        createEmail()
                    }
                }
                else
                {
                    Toast.makeText(
                        this,
                        "이메일과 비밀번호를 작성해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        private fun createEmail() {
            auth!!.createUserWithEmailAndPassword(
                emailEditText.text.toString().trim(),
                passwordEditText.text.toString().trim()
            ).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        val db = Firebase.firestore
                        //val user = auth?.currentUser
                        db.collection("user")
                            .document(UtilCode.getInstance().uid!!)
                            .set(hashMapOf("id" to "${emailEditText.text.toString().split('@')[0].trim()}"))
                        Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Authentication failed.${it.exception}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("sign in : ", "${it.exception}")
                    }
                }
        }

        private fun loginEmail() {
            auth!!.signInWithEmailAndPassword(
                emailEditText.text.toString().trim(),
                passwordEditText.text.toString().trim()
            )
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "signInWithEmail success.", Toast.LENGTH_SHORT).show()
                        val user = auth?.currentUser
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "signInWithEmail failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
