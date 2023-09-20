package com.example.githubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.githubuserapps.factory.SettingViewModelFactory
import com.example.githubuserapps.settings.SettingPreference
import com.example.githubuserapps.settings.SettingViewModel
import com.example.githubuserapps.settings.dataStore

class DarkModeSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_mode_setting)

        val themeSwitcher = findViewById<Switch>(R.id.dark_or_light)

        val settingPref = SettingPreference.getInstance(application.dataStore)

        val settingsViewModel = ViewModelProvider(this,SettingViewModelFactory(settingPref)).get(
            SettingViewModel::class.java
        )

        settingsViewModel.ThemeSettingGets().observe(this){
            dackModeActive: Boolean ->
            if(dackModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                themeSwitcher.isChecked = true
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                themeSwitcher.isChecked = false
            }

        }

        themeSwitcher.setOnCheckedChangeListener{ _: CompoundButton?, isSwitch: Boolean ->

           settingsViewModel.ThemeSettingsSave(isSwitch)

        }
    }
}