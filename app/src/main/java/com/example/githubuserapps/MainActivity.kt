package com.example.githubuserapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapps.databinding.ActivityMainBinding
import com.example.githubuserapps.databinding.ActivitySearchMenuBinding
import com.example.githubuserapps.factory.SettingViewModelFactory
import com.example.githubuserapps.settings.SettingPreference
import com.example.githubuserapps.settings.SettingViewModel
import com.example.githubuserapps.settings.dataStore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settingPref = SettingPreference.getInstance(application.dataStore)

        val settingsViewModel = ViewModelProvider(this, SettingViewModelFactory(settingPref)).get(
            SettingViewModel::class.java
        )

        settingsViewModel.ThemeSettingGets().observe(this){
                dackModeActive: Boolean ->
            if(dackModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        binding.btnMain.setOnClickListener{
            val getIntent = Intent(this@MainActivity,ActivitySearchMenu::class.java)
            startActivity(getIntent)
        }
    }
}