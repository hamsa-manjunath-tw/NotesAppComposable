package com.example.notesappcompose.viewmodel

import com.example.notesappcompose.data.model.Notes

data class NotesState(
    val notes : List<Notes> = emptyList()
)
