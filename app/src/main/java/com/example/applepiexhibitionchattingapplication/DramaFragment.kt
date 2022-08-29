package com.example.applepiexhibitionchattingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applepiexhibitionchattingapplication.databinding.FragmentDramaBinding

class DramaFragment : Fragment() {
    private lateinit var dramaAdapter: ChattingListRecyclerViewAdapter
    private var binding : FragmentDramaBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDramaBinding.inflate(layoutInflater)
        val view = binding?.root
        val dramaRecyclerView = binding!!.fragmentDramaChattingListRecyclerview//(id)findviewbyid.set
        var list: ArrayList<String> = arrayListOf("dfd","df","ddd","dfs")
        dramaAdapter = ChattingListRecyclerViewAdapter(list, requireActivity())
        dramaRecyclerView.adapter = dramaAdapter
        dramaRecyclerView.layoutManager = LinearLayoutManager(activity)
        // Inflate the layout for this fragment
        return view
        //inflater.inflate(R.layout.fragment_drama, container, false)
    }

}