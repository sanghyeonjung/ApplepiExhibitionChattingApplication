package com.example.applepiexhibitionchattingapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.applepiexhibitionchattingapplication.Chattingroom.ChattingListRecyclerViewAdapter
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private lateinit var movieAdapter: ChattingListRecyclerViewAdapter
    private var binding : FragmentMovieBinding? = null
    var list: MutableList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        val view = binding?.root
        val movieRecyclerView = binding!!.fragmentMovieChattingListRecyclerview//(id)findviewbyid.set
        movieAdapter = ChattingListRecyclerViewAdapter(list)
        movieRecyclerView.adapter = movieAdapter
        movieAdapter.apply {
            list.add("ddd")
            list.add("dddddd")
        }
        movieAdapter.notifyDataSetChanged()
        return view}
}