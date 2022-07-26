package com.example.notesappcompose.data.datasource

import com.example.notesappcompose.data.model.Notes
import kotlinx.coroutines.flow.Flow

interface LocalDatasource {
    suspend fun deleteNote(note: Notes)
    suspend fun addEditNote(id: Int, title: String, description: String)
    fun getNotes(): Flow<List<Notes>>
}