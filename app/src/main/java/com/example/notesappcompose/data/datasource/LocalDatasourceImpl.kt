package com.example.notesappcompose.data.datasource

import androidx.compose.runtime.toMutableStateList
import com.example.notesappcompose.data.model.Notes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

class LocalDatasourceImpl(private val noteDao: NoteDao) : LocalDatasource {

    override suspend fun deleteNote(note: Notes) {
        noteDao.deleteNote(note)
    }

    override suspend fun addEditNote(id: Int, title: String, description: String) {
        if (id == -1) {
            noteDao.insertNote(Notes(title, description = description))
        } else {
            val note = noteDao.getNoteById(id)
            note?.let {
                note.title = title
                note.description = description
                noteDao.update(note)
            }
        }
    }

    override fun getNotes(): Flow<List<Notes>> {
        return noteDao.getNotes()
    }
}