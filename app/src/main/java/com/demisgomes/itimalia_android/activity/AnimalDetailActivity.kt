package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.databinding.ActivityAnimalDetailBinding
import com.demisgomes.itimalia_android.databinding.ActivityLoginBinding
import com.demisgomes.itimalia_android.domain.animal.Animal

class AnimalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(animalKey)){
            val animal = intent.extras?.get(animalKey) as Animal

            Glide.with(this).load(animal.imageUrl).centerCrop().into(binding.imageViewAnimal)
            binding.textViewName.text = animal.name
            binding.textViewDescription.text = animal.description
            binding.textViewAgeValue.text =
                animal.age?.let {
                    resources.getQuantityString(R.plurals.animal_age,
                        it, it, animal.timeUnit.toString().lowercase())
                }
            binding.textViewSexValue.text = animal.sex.toString().lowercase()
            binding.textViewSizeValue.text = animal.size.toString().lowercase()
            binding.textViewCastratedValue.text = if (animal.castrated) "yes" else "no"
            binding.textViewStatusValue.text = animal.status.toString().lowercase()
        }
    }
}