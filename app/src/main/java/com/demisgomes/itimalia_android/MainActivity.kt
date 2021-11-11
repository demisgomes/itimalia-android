package com.demisgomes.itimalia_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.demisgomes.itimalia_android.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
//    private val viewModel: MainViewModel by viewModels(
//        factoryProducer = { SavedStateVMFactory(this) }
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.responseViewModel.observe(this){
            if (it.response?.isNotEmpty() == true) Log.d("MainActivity",
                it.response.first().toString()
            )

            else it.errorMessage?.let { message ->
                Log.d("MainActivity", message)
            }
        }
    }
}