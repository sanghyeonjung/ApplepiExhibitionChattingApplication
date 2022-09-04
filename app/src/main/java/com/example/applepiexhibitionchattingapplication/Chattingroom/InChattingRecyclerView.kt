package com.example.applepiexhibitionchattingapplication.Chattingroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.InChattingItemBinding

class InChattingRecyclerView :
    RecyclerView.Adapter<InChattingRecyclerView.ViewHolder>() {
    val data = mutableListOf<String>()
    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun onBind(item:String){
            val textview = view.findViewById<TextView>(R.id.inChattingItemTextview)
            textview.text = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.in_chatting_item,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

}