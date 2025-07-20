package com.deesha.notetakingapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)
    @Update
    suspend fun updateNote(note: Note)
    @Delete
    suspend fun deleteNote(note: Note)
    @Query("SELECT * FROM Note ORDER BY id desc")
    fun getAllNotes():LiveData<List<Note>>
    @Query("SELECT * FROM Note where title like:query or body like:query")
    fun searchNote(query:String?):LiveData<List<Note>>
}