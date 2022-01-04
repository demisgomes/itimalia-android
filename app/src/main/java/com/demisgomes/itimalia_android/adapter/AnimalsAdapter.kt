package com.demisgomes.itimalia_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.domain.animal.Animal
import kotlin.random.Random

class AnimalsAdapter(private val animalsList: List<Animal>, val onClick: (animal: Animal) -> Unit = {}) : RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder>() {

    private val dogImages = listOf("https://images.dog.ceo/breeds/hound-basset/n02088238_3066.jpg", "https://images.dog.ceo/breeds/hound-basset/n02088238_10110.jpg", "https://images.dog.ceo/breeds/hound-basset/n02088238_4182.jpg", "https://images.dog.ceo/breeds/hound-english/n02089973_1076.jpg", "https://images.dog.ceo/breeds/hound-ibizan/n02091244_3153.jpg", "https://images.dog.ceo/breeds/hound-english/n02089973_319.jpg", "https://images.dog.ceo/breeds/hound-ibizan/n02091244_746.jpg", "https://images.dog.ceo/breeds/hound-afghan/n02088094_3051.jpg")

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val animalCardView : CardView = itemView.findViewById(R.id.animalCardView)
        val animalImageView: ImageView = itemView.findViewById(R.id.animalItemImageView)
        val animalNameTextView: TextView = itemView.findViewById(R.id.animalItemNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalsList[position]
        holder.animalNameTextView.text = animal.name
        Glide.with(holder.itemView.context).load(dogImages[position]).centerCrop().into(holder.animalImageView)
        holder.animalCardView.setOnClickListener{
            animal.imageUrl = dogImages[position]
            onClick(animal)
        }
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }
}