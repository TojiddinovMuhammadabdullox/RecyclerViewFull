package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemListLayoutBinding

class MtCystomAdapter(val list: MutableList<String>) :
    RecyclerView.Adapter<MtCystomAdapter.MyViewHolder>() {
        private var recyclerViewListener:RecyclerViewListener? = null

    inner class MyViewHolder(val binding: ItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {

                binding.deleteBtn.setOnClickListener {
                    recyclerViewListener?.onDeleteButtonClick(adapterPosition)
                }
            }

        fun bind(title: String) {
            binding.titleTv.text = title
        }
    }

    fun setListener(listener: RecyclerViewListener){
        recyclerViewListener = listener
    }

    private class MyDiffUtil(val oldList: List<String>, val newList: List<String>):DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldListSize
        }

        override fun getNewListSize(): Int {
            return newListSize
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]===newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition]==newList[newItemPosition]
        }
    }

    fun submitList(newList: List<String>){
        val resultDiffUtils = DiffUtil.calculateDiff(MyDiffUtil(list, newList))
        list.clear()
        list.addAll(newList)
        resultDiffUtils.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = list[position]
        holder.bind(title)
    }
}