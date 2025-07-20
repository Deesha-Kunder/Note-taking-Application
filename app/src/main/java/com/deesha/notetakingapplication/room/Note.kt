package com.deesha.notetakingapplication.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var body:String
):Parcelable{}
