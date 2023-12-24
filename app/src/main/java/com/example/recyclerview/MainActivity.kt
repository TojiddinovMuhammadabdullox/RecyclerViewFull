package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = mutableListOf<String>()
        val customAdapter = MtCystomAdapter(list)

        val myLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.apply {
            adapter = customAdapter
            layoutManager = myLayoutManager
            addItemDecoration(DividerItemDecoration(this@MainActivity, myLayoutManager.orientation))
        }


        customAdapter.setListener(object : RecyclerViewListener {
            override fun onItemClick(position: Int) {

            }

            override fun onDeleteButtonClick(position: Int) {
                list.removeAt(position)
                customAdapter.notifyItemRemoved(position)
            }
        })
        val itemTouchHelper = ItemTouchHelper(object : MyTouchItemHelper() {
            override fun onItemSwipeToDelete(position: Int) {

                val removedTitle = list.removeAt(position)
                customAdapter.notifyItemRemoved(position)

                Snackbar.make(
                    this@MainActivity,
                    binding.recyclerView,
                    "$removedTitle is going to be removed",
                    Snackbar.LENGTH_LONG
                ).setAction("Tiklash"){
                    list.add(position, removedTitle)
                    customAdapter.notifyItemInserted(position)
                }.show()
            }

            override fun onItemMoved(from: Int, to: Int) {
                Collections.swap(list,from,to)
                customAdapter.notifyItemMoved(from,to)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.addBtn.setOnClickListener {
            val title = binding.titleEt.text.toString()
            val newList = mutableListOf<String>()
            newList.addAll(list)
            if (title.isNotBlank()) {
                newList.add(title.trim())
                customAdapter.submitList(newList)
                binding.titleEt.text.clear()
            }
        }
    }
}