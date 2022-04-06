package com.rai.calculator


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rai.calculator.databinding.ItemLogBinding

class LogsAdapter(private  val items: List<String>): RecyclerView.Adapter<LogsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val  layoutInflater = LayoutInflater.from( parent.context)
        return  LogsViewHolder(
            binding = ItemLogBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
       return  items.size
    }


}

class LogsViewHolder(private  val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: String){
        binding.textLog.text = item
    }

}