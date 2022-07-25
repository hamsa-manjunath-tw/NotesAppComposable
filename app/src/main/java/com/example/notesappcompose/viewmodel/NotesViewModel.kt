package com.example.notesappcompose.viewmodel

import android.provider.ContactsContract
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.notesappcompose.data.Notes

class NotesViewModel : ViewModel() {
    private var notesSample = mutableListOf(
        Notes(1, "Note 1", "Description for Note1"),
        Notes(2, "Note 2", "Description for Note2"),
        Notes(3, "Note 3", "Description for Note3"),
        Notes(4, "Note 4", "Description for Note4"),
        Notes(5, "Note 5", "Description for Note5"),
        Notes(6, "Note 6", "Description for Note6"),
        Notes(7, "Note 7", "Description for Note7"),
        Notes(8, "Note 8", "Description for Note8"),
        Notes(9, "Note 9", "Description for Note9"),
        Notes(10, "Note 10", "Description for Note10")
    )

    private var _notesList = notesSample.toMutableStateList()
    val notesList: List<Notes>
        get() = _notesList


    fun addEditNotes(id: Int, title: String, description: String) {
        if (id == -1) {
            //  notesSample.add(Notes(notesSample.size, title, description))
            _notesList.add(Notes(_notesList.size+1, title = title, description = description))
        } else {
            val note = _notesList.find { note ->
                note.id == id
            }
            note?.let {
                note.title = title
                note.description = description
            }

        }

    }

    fun deleteNote(note: Notes) {
        _notesList.remove(note)
    }


}