package com.example.notesappcompose.data.repoImpl

import androidx.compose.runtime.toMutableStateList
import com.example.notesappcompose.data.datasource.LocalDatasource
import com.example.notesappcompose.domain.repoInterface.NotesRepository
import com.example.notesappcompose.data.model.Notes
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val localDatasource: LocalDatasource): NotesRepository {
    override suspend fun deleteNote(note: Notes) {
        localDatasource.deleteNote(note = note)
    }

    override suspend fun addEditNote(id: Int, title: String, description: String) {
        localDatasource.addEditNote(id = id, title = title, description = description)
    }

    override fun getNotes(): Flow<List<Notes>> {
       return localDatasource.getNotes()
    }


}