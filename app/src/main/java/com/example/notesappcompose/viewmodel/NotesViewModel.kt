package com.example.notesappcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesappcompose.data.model.Notes
import com.example.notesappcompose.data.repoImpl.NotesRepositoryImpl
import com.example.notesappcompose.domain.repoInterface.NotesRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    private val _notesList = MutableStateFlow<List<Notes>>(emptyList())
    val notesList : StateFlow<List<Notes>> =  _notesList

    init {
        getNotes()
    }
    private fun getNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getNotes()
                .catch { e->
                    //Log error here
                }
                .collect {
                    _notesList.value = it
                }
        }
    }

    fun addEditNotes(id: Int, title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.addEditNote(id = id, title = title, description = description)
            getNotes()
        }
    }

    fun deleteNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(note = note)
            getNotes()
        }
    }



}