package com.example.githubuserapps.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.database.LovedDao
import com.example.githubuserapps.database.LovedRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LovedRepository(application: Application) {
    private val mLovedDao: LovedDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = LovedRoomDatabase.getDatabase(application)
        mLovedDao = db.lovedDao()
    }

    fun getAllNotes(): LiveData<List<Loved>> = mLovedDao.getAllLoved()

    fun insert(loved: Loved) {
        executorService.execute { mLovedDao.insert(loved) }
    }
    fun delete(loved: Loved) {
        executorService.execute { mLovedDao.delete(loved) }
    }
    fun update(loved: Loved) {
        executorService.execute { mLovedDao.update(loved) }
    }

}