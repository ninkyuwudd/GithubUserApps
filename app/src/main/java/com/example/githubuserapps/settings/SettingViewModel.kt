package com.example.githubuserapps.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingViewModel(private val settingsPref: SettingPreference): ViewModel() {
    fun ThemeSettingGets(): LiveData<Boolean>{
        return  settingsPref.getThemeSetting().asLiveData()
    }

    fun ThemeSettingsSave(activeDarkMode : Boolean){
        viewModelScope.launch {
            settingsPref.saveThemeSetting(activeDarkMode)
        }
    }
}