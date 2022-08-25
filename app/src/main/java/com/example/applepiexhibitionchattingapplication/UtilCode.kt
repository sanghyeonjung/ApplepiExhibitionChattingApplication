package com.example.applepiexhibitionchattingapplication

import com.google.firebase.auth.FirebaseAuth

object UtilCode {
    fun getInstance() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}