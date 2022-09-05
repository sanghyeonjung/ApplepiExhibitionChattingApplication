package com.example.applepiexhibitionchattingapplication.Chattingroom

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.ChattingItemBinding
import com.example.applepiexhibitionchattingapplication.databinding.InChattingItemBinding

class InChattingRecyclerView :
    RecyclerView.Adapter<InChattingRecyclerView.ViewHolder>() {
    /*fun setListData(datas:MutableList<String>){
        data = datas
    }*/
    private lateinit var binding: InChattingItemBinding
    val data = mutableListOf<String>()
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun OnBind(item:String){
            binding.inChattingItemTextview.setText("${item}")
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.in_chatting_item,parent,false)
        return ViewHolder()
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.OnBind(data[position])
    }

}