package com.example.applepiexhibitionchattingapplication

import android.app.Application

class MyApp : Application() {
    public var currentGenre : String = "webtoon"
    public fun setGenre(s : String)
    {
        currentGenre = s
    }
}