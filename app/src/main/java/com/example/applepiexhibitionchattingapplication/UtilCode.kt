package com.example.applepiexhibitionchattingapplication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UtilCode {
    fun getInstance() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    var currentGenre = "webtoon"
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    var uid = auth.currentUser?.uid
}