package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemGalleryLayoutBinding

class GalleryAdapter(val imageList: List<Int>) :
    RecyclerView.Adapter<GalleryAdapter.MyGalleryViewHolder>() {

        private var recyclerViewListener:RecyclerViewListener? = null

    inner class MyGalleryViewHolder(val binding: ItemGalleryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    recyclerViewListener?.onItemClick(adapterPosition)
                }
            }

        fun bind(imageId: Int) {
            binding.imageView.setImageResource(imageId)
        }
    }

    fun setLisetener(listener: RecyclerViewListener){
        recyclerViewListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGalleryViewHolder {
        return MyGalleryViewHolder(
            ItemGalleryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyGalleryViewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}