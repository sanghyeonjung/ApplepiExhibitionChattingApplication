package com.example.applepiexhibitionchattingapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.applepiexhibitionchattingapplication.Chattingroom.ChattingListRecyclerViewAdapter
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.FragmentWebtoonBinding

class WebtoonFragment : Fragment() {
    private lateinit var webtoonAdapter: ChattingListRecyclerViewAdapter
    private var binding : FragmentWebtoonBinding? = null
    var list: MutableList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webtoon, container, false)
        val view = binding?.root
        val webtoonRecyclerView = binding!!.fragmentWebtoonChattingListRecyclerview//(id)findviewbyid.set
        webtoonAdapter = ChattingListRecyclerViewAdapter(list)
        webtoonRecyclerView.adapter = webtoonAdapter
        webtoonAdapter.apply {
            list.add("ddss")
        }
        webtoonAdapter.notifyDataSetChanged()
        return view
    }

}