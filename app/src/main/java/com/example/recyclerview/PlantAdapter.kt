package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.PlantItemBinding

class PlantAdapter: RecyclerView.Adapter<PlantAdapter.PlantHolder>() {

    val plantList = ArrayList<Plant>()

    class PlantHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = PlantItemBinding.bind(item)


        //заполняем один элемент списка
        fun bind(plant: Plant) = with(binding){
            im.setImageResource(plant.imageId)
            tvTitle.text = plant.title
        }
    }

    override fun onViewAttachedToWindow(holder: PlantHolder)  {
        holder.itemView.setOnClickListener {
            MainActivity.toStart()
        }
    }

    override fun onViewDetachedFromWindow(holder: PlantHolder) {
        holder.itemView.setOnClickListener(null)
    }


    //берет разметку, надувает ее с помощью inflate и создает класс PlantHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        //LayoutInflater.from(parent.context). - пишем чтобы взять инфлейтерБ тк мы находимя не на каком-ниудь активити
        // в main мы бы просто написали layoutInflater.inflate
        //R.layout.plant_item - ресурс, который мы хотим надуть
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantHolder(view)
    }


    //запускается после onCreateViewHolder
    //onBindViewHolder позволяет доступ к элементам и заполнить их
    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {
        return plantList.size
    }



    public fun addPlant(plant: Plant){ // добавление одного растения
        plantList.add(plant)
        notifyDataSetChanged() //обновление адаптера, тк добавились данные и надо перерисвоат список
    }


    public fun addAll(list: List<Plant>){ //добавление списка с растениями 
        plantList.clear()
        plantList.addAll(list)
        notifyDataSetChanged()
    }
}