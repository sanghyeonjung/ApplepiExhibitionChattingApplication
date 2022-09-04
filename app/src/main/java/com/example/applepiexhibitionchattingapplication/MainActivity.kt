package com.example.applepiexhibitionchattingapplication
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.applepiexhibitionchattingapplication.Chattingroom.AddChatRoomActivity
import com.example.applepiexhibitionchattingapplication.Chattingroom.ChattingListRecyclerViewAdapter
import com.example.applepiexhibitionchattingapplication.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var chattingListAdapter: ChattingListRecyclerViewAdapter
    val database : DatabaseReference = Firebase.database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val fragmentWebtoonBtn = findViewById<Button>(R.id.mainButtonFragmentWebtoon)
        val fragmentMovieBtn = findViewById<Button>(R.id.mainButtonFragmentMovie)
        val fragmentDramaBtn = findViewById<Button>(R.id.mainButtonFragmentDrama)
        val optionBtn = findViewById<ImageButton>(R.id.mainOptionImageButton)
        val addBtn = findViewById<ImageButton>(R.id.mainAddImageButton)

        chattingListAdapter = ChattingListRecyclerViewAdapter()
        binding.mainChattingRecyclerView.adapter = chattingListAdapter

            optionBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,OptionActivity::class.java))
        }
        addBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, AddChatRoomActivity::class.java))
        }
        fragmentWebtoonBtn.setOnClickListener {
            setData("webtoon")
            UtilCode.currentGenre = "webtoon"

            Log.e("currentGenre","${UtilCode.currentGenre}")
            fragmentWebtoonBtn.setBackgroundResource(R.drawable.custom_round_click_fragmentbutton)
            fragmentWebtoonBtn.setTextColor(Color.parseColor("#ffffff"))
            fragmentDramaBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentMovieBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentDramaBtn.setTextColor(Color.parseColor("#ff5959"))
            fragmentMovieBtn.setTextColor(Color.parseColor("#ff5959"))
        }
        fragmentMovieBtn.setOnClickListener {
            setData("movie")
            UtilCode.currentGenre = "movie"

            Log.e("currentGenre","${UtilCode.currentGenre}")
            fragmentMovieBtn.setBackgroundResource(R.drawable.custom_round_click_fragmentbutton)
            fragmentMovieBtn.setTextColor(Color.parseColor("#ffffff"))
            fragmentDramaBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentWebtoonBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentDramaBtn.setTextColor(Color.parseColor("#ff5959"))
            fragmentWebtoonBtn.setTextColor(Color.parseColor("#ff5959"))
        }
        fragmentDramaBtn.setOnClickListener {
            setData("drama")
            UtilCode.currentGenre = "drama"
            Log.e("currentGenre","${UtilCode.currentGenre}")
            fragmentDramaBtn.setBackgroundResource(R.drawable.custom_round_click_fragmentbutton)
            fragmentDramaBtn.setTextColor(Color.parseColor("#ffffff"))
            fragmentWebtoonBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentMovieBtn.setBackgroundResource(R.drawable.custom_round_unclicked_fragmentbutton)
            fragmentWebtoonBtn.setTextColor(Color.parseColor("#ff5959"))
            fragmentMovieBtn.setTextColor(Color.parseColor("#ff5959"))
        }
    }

    private fun setData(genre : String){
            database.child(genre).get().addOnSuccessListener {
                it.value?.let { value ->
                    val result = value as HashMap<String,Any>?
                    if (result != null) {
                        chattingListAdapter.apply {
                            data.clear()
                            notifyDataSetChanged()
                        }
                        for(key in result.keys) {
                            if(key != "cnt")
                            {
                                chattingListAdapter.apply {
                                    data.add(key)
                                    Log.d("data","$key")
                                }
                            }
                        }
                        chattingListAdapter.notifyDataSetChanged()
                    }
                }
            }.addOnFailureListener {
                Log.e("firebase","Error getting data", it)
            }
    }

}