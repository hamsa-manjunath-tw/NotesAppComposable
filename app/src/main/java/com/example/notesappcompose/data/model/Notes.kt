package com.example.notesappcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes( var title: String, var description: String){
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}