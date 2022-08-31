package com.example.applepiexhibitionchattingapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.applepiexhibitionchattingapplication.Chattingroom.ChattingListRecyclerViewAdapter
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.FragmentDramaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DramaFragment : Fragment() {
    private lateinit var dramaAdapter: ChattingListRecyclerViewAdapter
    private var binding : FragmentDramaBinding? = null
    private var list: MutableList<String> = arrayListOf()

    val database = Firebase.database
    val myRef = database.getReference("drama")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drama, container, false)
        val view = binding?.root
        dramaAdapter = ChattingListRecyclerViewAdapter(list)
        binding!!.fragmentDramaChattingListRecyclerview.adapter = dramaAdapter
        // Inflate the layout for this fragment
        myRef.addValueEventListener(object : ValueEventListener{
            val listData : MutableList<String> = mutableListOf<String>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    list.clear()
                    for(chatListSnapshot in snapshot.children){
                        val getData = chatListSnapshot.getValue(String::class.java)
                        listData.add(getData!!)
                        dramaAdapter.apply {
                            list.add(getData!!)
                        }
                    }
                }}

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return view
        //inflater.inflate(R.layout.fragment_drama, container, false)
    }

}