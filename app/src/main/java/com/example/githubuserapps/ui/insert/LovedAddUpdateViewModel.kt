package com.example.githubuserapps.ui.insert

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.repository.LovedRepository

class LovedAddUpdateViewModel(application: Application): ViewModel() {

    private val mNoteRepository: LovedRepository = LovedRepository(application)

    fun getAllLovedByName(username: String) : LiveData<Loved> = mNoteRepository.getAllLovedByName(username)

    fun insert(loved: Loved) {
        mNoteRepository.insert(loved)
    }
    fun update(loved: Loved) {
        mNoteRepository.update(loved)
    }
    fun delete(loved: Loved) {
        mNoteRepository.delete(loved)
    }


}