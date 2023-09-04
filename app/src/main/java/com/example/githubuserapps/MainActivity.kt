package com.example.githubuserapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserapps.databinding.ActivityMainBinding
import com.example.githubuserapps.databinding.ActivitySearchMenuBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener{
            val getIntent = Intent(this@MainActivity,ActivitySearchMenu::class.java)
            startActivity(getIntent)
        }
    }
}