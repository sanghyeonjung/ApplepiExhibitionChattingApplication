package com.example.applepiexhibitionchattingapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class WebtoonFragment : Fragment() {
    private lateinit var recyclerViewAdapter: ChattingListRecyclerViewAdapter
    private lateinit var webtoonRecyclerview :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var list: ArrayList<String> = arrayListOf("dd","df")
        recyclerViewAdapter = ChattingListRecyclerViewAdapter(list, requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webtoon, container, false)
    }

    private val nextActivity = {
        startActivity(Intent(activity, ChattingActivity::class.java))
    }

}