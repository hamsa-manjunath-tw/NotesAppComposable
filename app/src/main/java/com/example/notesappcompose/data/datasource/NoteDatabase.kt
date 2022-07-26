package com.example.notesappcompose.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesappcompose.data.model.Notes

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDataBase : RoomDatabase() {
    abstract val noteDao:NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}