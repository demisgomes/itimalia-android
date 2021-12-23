package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.demisgomes.itimalia_android.R

class AnimalDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)

        val textView : TextView = findViewById(R.id.textView)

        if (intent.hasExtra("name")){
            val name = intent.extras?.get("name") as String
            textView.text = name
        }
    }
}