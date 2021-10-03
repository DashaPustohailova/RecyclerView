package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding


lateinit var binding : ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private val adapter = PlantAdapter()

    private var editLauncher : ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        //получаем результат из EditActivity
        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                adapter.addPlant(it.data?.getSerializableExtra("plant") as Plant)
            }
        }

    }



    //вместо = with(binding) можно написать внутри функции binding.apply{ тут код }
    private fun init() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
//            GridLayoutManager(this@MainActivity, 3) // 3 - т.е. у нас будет три колонки элементов
        rcView.adapter = adapter //выбираем адаптер

        btAdd.setOnClickListener {
            //запускаем EditActivity
            editLauncher?.launch(Intent(this@MainActivity,  EditActivity::class.java))
        }


    }

    companion object {
        fun toStart(){
           binding.rcView.smoothScrollToPosition(0)
        }
    }

}