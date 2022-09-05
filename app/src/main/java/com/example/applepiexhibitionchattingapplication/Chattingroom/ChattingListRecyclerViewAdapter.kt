package com.example.applepiexhibitionchattingapplication.Chattingroom
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.ChattingItemBinding

class ChattingListRecyclerViewAdapter :
    RecyclerView.Adapter<ChattingListRecyclerViewAdapter.ViewHolder>() {
    private lateinit var binding:ChattingItemBinding
    val data = mutableListOf<String>()
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun OnBind(item:String){
            binding.itemBtn.setText("${item}")
            binding.itemBtn.setOnClickListener {
                val intent = Intent(binding.root.context, ChattingActivity::class.java)
                intent.putExtra("chatroomname",item)
                binding.root.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.chatting_item,parent,false)
        Log.e("oncreateviewholder","create success")
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.OnBind(data[position])
        Log.e("onbindviewholder ", data.size.toString())
    }

    override fun getItemCount(): Int = data.size

}