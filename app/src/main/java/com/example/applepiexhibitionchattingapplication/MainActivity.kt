package com.example.applepiexhibitionchattingapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentWebtoonBtn = findViewById<Button>(R.id.mainButtonFragmentWebtoon)
        val fragmentMovieBtn = findViewById<Button>(R.id.mainButtonFragmentMovie)
        val fragmentDramaBtn = findViewById<Button>(R.id.mainButtonFragmentDrama)

        fragmentWebtoonBtn.setOnClickListener {
            fragmentWebtoonBtn.setBackgroundResource(R.drawable.custom_round_click_fragmentbutton)
            fragmentWebtoonBtn.setTextColor(Color.parseColor("#ffffff"))

            fragmentDramaBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentMovieBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentDramaBtn.setTextColor(Color.parseColor("#ff5959"))
            fragmentMovieBtn.setTextColor(Color.parseColor("#ff5959"))
            replaceFragment(WebtoonFragment())
        }
        fragmentMovieBtn.setOnClickListener {
            fragmentMovieBtn.setBackgroundResource(R.drawable.custom_round_click_fragmentbutton)
            fragmentMovieBtn.setTextColor(Color.parseColor("#ffffff"))

            fragmentDramaBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentWebtoonBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentDramaBtn.setTextColor(Color.parseColor("#ff5959"))
            fragmentWebtoonBtn.setTextColor(Color.parseColor("#ff5959"))
            replaceFragment(MovieFragment())
        }
        fragmentDramaBtn.setOnClickListener {
            fragmentDramaBtn.setBackgroundResource(R.drawable.custom_round_click_fragmentbutton)
            fragmentDramaBtn.setTextColor(Color.parseColor("#ffffff"))

            fragmentWebtoonBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentMovieBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentWebtoonBtn.setTextColor(Color.parseColor("#ff5959"))
            fragmentMovieBtn.setTextColor(Color.parseColor("#ff5959"))
            replaceFragment(DramaFragment())
        }
    }
    private fun replaceFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragmentChangeFrameLayout, fragment)
        transaction.commit()
    }
}