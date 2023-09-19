package com.example.githubuserapps.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
class Loved (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "account_username")
    var account_username: String = "",
    @ColumnInfo(name = "avatarImgUrl")
    var avatarImgUrl: String? = null
):Parcelable