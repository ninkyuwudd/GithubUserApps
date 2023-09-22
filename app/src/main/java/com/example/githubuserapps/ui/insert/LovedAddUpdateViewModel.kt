package com.example.githubuserapps.ui.insert

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.repository.LovedRepository

class LovedAddUpdateViewModel(application: Application): ViewModel() {

    private val mLovedRepository: LovedRepository = LovedRepository(application)

    fun getAllLovedByName(username: String) : LiveData<Loved> = mLovedRepository.getAllLovedByName(username)

    fun insert(loved: Loved) {
        mLovedRepository.insert(loved)
    }
    fun update(loved: Loved) {
        mLovedRepository.update(loved)
    }
    fun delete(loved: Loved) {
        mLovedRepository.delete(loved)
    }


}