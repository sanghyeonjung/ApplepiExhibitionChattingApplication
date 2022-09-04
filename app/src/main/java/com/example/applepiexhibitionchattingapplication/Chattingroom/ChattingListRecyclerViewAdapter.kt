package com.example.applepiexhibitionchattingapplication.Chattingroom
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.ChattingItemBinding

class ChattingListRecyclerViewAdapter :
    RecyclerView.Adapter<ChattingListRecyclerViewAdapter.ViewHolder>() {
    val data = mutableListOf<String>()
    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun onBind(item:String){
            val btn = view.findViewById<Button>(R.id.item_btn)
            btn.text = item

            btn.setOnClickListener {
                val intent = Intent(view.context, ChattingActivity::class.java)
                Log.d("click ","$item")
                intent.putExtra("chatroomname",item)
                view.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chatting_item, parent, false)
        Log.e("oncreateviewholder","create success")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
        Log.e("onbindviewholder ", data.size.toString())
    }

    override fun getItemCount(): Int = data.size

}