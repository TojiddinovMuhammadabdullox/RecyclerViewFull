package com.example.recyclerview

interface RecyclerViewListener {
    fun onItemClick(position:Int)
    fun onDeleteButtonClick(position: Int)
}