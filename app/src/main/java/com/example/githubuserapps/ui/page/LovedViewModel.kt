package com.example.githubuserapps.ui.page

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.repository.LovedRepository

class LovedViewModel(application: Application): ViewModel() {

    private val mLovedRepository: LovedRepository = LovedRepository(application)
    fun getAllLoved(): LiveData<List<Loved>> = mLovedRepository.getAllLoved()

    fun getAllLovedByUsername(username : String) : LiveData<Loved> =mLovedRepository.getAllLovedByName(username)
}