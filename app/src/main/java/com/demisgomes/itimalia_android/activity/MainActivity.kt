package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.adapter.AnimalsAdapter
import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.domain.animal.Specie
import com.demisgomes.itimalia_android.domain.animal.TimeUnit
import com.demisgomes.itimalia_android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.animals_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.responseViewModel.observe(this){
            val animalsAdapter = it.response?.let { it1 -> AnimalsAdapter(it1) { animalName ->
                    Toast.makeText(this, animalName, Toast.LENGTH_SHORT).show()
                }
            }
            recyclerView.adapter = animalsAdapter

            if (it.response?.isNotEmpty() == true) Log.d("MainActivity",
                it.response.first().toString()
            )

            else it.errorMessage?.let { message ->
                Log.d("MainActivity", message)
            }
        }
    }
}