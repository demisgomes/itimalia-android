package com.demisgomes.itimalia_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.domain.animal.Animal

class AnimalsAdapter(private val animalsList: List<Animal>) : RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val animalImageView: ImageView = itemView.findViewById(R.id.animalItemImageView)
        val animalNameTextView: TextView = itemView.findViewById(R.id.animalItemNameTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.animalNameTextView.text = animalsList[position].name
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }
}