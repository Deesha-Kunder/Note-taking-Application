package com.deesha.notetakingapplication.repository

import androidx.lifecycle.LiveData
import com.deesha.notetakingapplication.room.Note
import com.deesha.notetakingapplication.room.NoteDao
import com.deesha.notetakingapplication.room.NoteDatabase

class MyRepository(private val db:NoteDatabase) {
    private val noteDao = db.getNoteDao()
    suspend fun addNote(note: Note) = noteDao.addNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note)= noteDao.deleteNote(note)

    fun getAllNotes() = noteDao.getAllNotes()

    fun search(query:String?) = noteDao.searchNote(query)

}