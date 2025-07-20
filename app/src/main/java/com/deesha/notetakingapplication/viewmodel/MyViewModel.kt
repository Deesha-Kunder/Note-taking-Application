package com.deesha.notetakingapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.deesha.notetakingapplication.repository.MyRepository
import com.deesha.notetakingapplication.room.Note
import kotlinx.coroutines.launch

class MyViewModel(app:Application,private val repository:MyRepository):AndroidViewModel(app) {

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }
    fun deleteNote(note:Note) =viewModelScope.launch {
        repository.deleteNote(note)
    }
    fun updateNote(note:Note) =viewModelScope.launch {
        repository.updateNote(note)
    }
    fun getAllNote() = repository.getAllNotes()
    fun searchNote(query: String?) = repository.search(query)


}