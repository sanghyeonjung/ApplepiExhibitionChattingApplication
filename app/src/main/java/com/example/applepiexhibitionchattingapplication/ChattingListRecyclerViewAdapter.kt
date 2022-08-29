package com.example.applepiexhibitionchattingapplication
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ChattingListRecyclerViewAdapter(private val data: List<String>, private val activity: Activity) : RecyclerView.Adapter<ChattingListRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(val row : View) : RecyclerView.ViewHolder(row){
        val button : Button = row.findViewById<Button>(R.id.item_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChattingListRecyclerViewAdapter.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.chatting_list_item,parent,false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ChattingListRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.button.text = data.get(position)
        holder.button.setOnClickListener(View.OnClickListener {
            activity.startActivity(Intent(activity.applicationContext, ChattingActivity::class.java))
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}