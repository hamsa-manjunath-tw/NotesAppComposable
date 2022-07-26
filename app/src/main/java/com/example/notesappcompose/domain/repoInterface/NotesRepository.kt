package com.example.notesappcompose.domain.repoInterface

import com.example.notesappcompose.data.model.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun deleteNote(note: Notes)
    suspend fun addEditNote(id: Int, title: String, description: String)
    fun getNotes(): Flow<List<Notes>>
}