package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recyclerview.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditBinding
    private var indexImage = 0
    private var imageId = R.drawable.one
    private val imageIdList = listOf(
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four,
        R.drawable.five,
        R.drawable.six,
        R.drawable.seven,
        R.drawable.eight,
        R.drawable.nine
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }

    private fun initButtons() = with(binding){
        bNext.setOnClickListener {
            indexImage++
            if(indexImage > imageIdList.size - 1){
                indexImage = 0
            }
            imageId = imageIdList[indexImage]
            imageView5.setImageResource(imageId)
            Log.d("MyLog", "Index $indexImage")
        }

        bDone.setOnClickListener {
            val plant = Plant(imageId, edTitle.text.toString(), edDisk.text.toString())
            val editIntent = Intent().apply {
                putExtra("plant", plant)
            }
            setResult(RESULT_OK, editIntent)
            finish()
        }
    }
}