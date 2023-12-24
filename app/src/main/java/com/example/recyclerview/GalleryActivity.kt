package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf<Int>(
            R.drawable.banan,
            R.drawable.nok,
            R.drawable.orik,
            R.drawable.qovun,
            R.drawable.shaftoli,
            R.drawable.uzum,
            R.drawable.banan,
            R.drawable.nok,
            R.drawable.orik,
            R.drawable.qovun,
            R.drawable.shaftoli,

        )
        val gallaeryAdapter = GalleryAdapter(list)
        val myManager = StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL)

        binding.recyclerView.apply {
            adapter = gallaeryAdapter
            layoutManager = myManager
        }

        gallaeryAdapter.setLisetener(object :RecyclerViewListener{
            override fun onItemClick(position: Int) {
                Intent(this@GalleryActivity,ImageViewActivity::class.java).apply {
                    putExtra("image", list[position])
                    startActivity(this)

                }
            }

            override fun onDeleteButtonClick(position: Int) {

            }
        })

    }
}