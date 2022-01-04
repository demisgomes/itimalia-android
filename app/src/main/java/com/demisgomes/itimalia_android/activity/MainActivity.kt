package com.demisgomes.itimalia_android.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.adapter.AnimalsAdapter
import com.demisgomes.itimalia_android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val animalKey = "animal";

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.animals_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.responseViewModel.observe(this){
            val animalsAdapter = it.response?.let { it1 -> AnimalsAdapter(it1) { animal ->
                    val intent = Intent(this, AnimalDetailActivity::class.java)
                    intent.putExtra(animalKey, animal)
                    startActivity(intent)
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