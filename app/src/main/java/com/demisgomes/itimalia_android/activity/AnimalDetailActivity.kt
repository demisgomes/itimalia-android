package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.domain.animal.Animal

class AnimalDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)

        val imageViewAnimal : ImageView = findViewById(R.id.imageViewAnimal)
        val textViewName : TextView = findViewById(R.id.textViewName)
        val textViewDescription : TextView = findViewById(R.id.textViewDescription)
        val textViewAge : TextView = findViewById(R.id.textViewAgeValue)
        val textViewSex : TextView = findViewById(R.id.textViewSexValue)
        val textViewSize : TextView = findViewById(R.id.textViewSizeValue)
        val textViewCastrated : TextView = findViewById(R.id.textViewCastratedValue)
        val textViewStatus : TextView = findViewById(R.id.textViewStatusValue)


        if (intent.hasExtra(animalKey)){
            val animal = intent.extras?.get(animalKey) as Animal

            //Glide.with(this).load(animal.imageUrl).centerCrop().into(imageViewAnimal)
            textViewName.text = animal.name
            textViewDescription.text = animal.description
            textViewAge.text = "${animal.age} ${animal.timeUnit.toString().lowercase()}s"
            textViewSex.text = animal.sex.toString().lowercase()
            textViewSize.text = animal.size.toString().lowercase()
            textViewCastrated.text = if (animal.castrated) "yes" else "no"
            textViewStatus.text = animal.status.toString().lowercase()
        }
    }
}