package com.example.notesappcompose.data.datasource

import androidx.room.*
import com.example.notesappcompose.data.model.Notes
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id:Int):Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)

    @Update
    fun update(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)
}