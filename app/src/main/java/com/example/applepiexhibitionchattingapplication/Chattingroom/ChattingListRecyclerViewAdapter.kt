package com.example.applepiexhibitionchattingapplication.Chattingroom
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applepiexhibitionchattingapplication.R
import com.example.applepiexhibitionchattingapplication.databinding.ChattingItemBinding

class ChattingListRecyclerViewAdapter(private var data: MutableList<String>) :
    RecyclerView.Adapter<ChattingListRecyclerViewAdapter.ViewHolder>() {
    fun setListData(datas:MutableList<String>){
        data = datas
    }
    private lateinit var binding:ChattingItemBinding
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        init {
            binding.itemBtn.setOnClickListener {
                val intent = Intent(binding.root.context, ChattingActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }
        fun OnBind(item:String){
            binding.itemBtn.text = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.chatting_item,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.OnBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}