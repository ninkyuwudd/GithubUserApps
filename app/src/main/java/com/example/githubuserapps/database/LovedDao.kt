package com.example.githubuserapps.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface LovedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(loved: Loved)

    @Update
    fun update(loved: Loved)

    @Delete
    fun delete(loved: Loved)

    @Query("SELECT * FROM loved")
    fun getAllLoved() : LiveData<List<Loved>>
}