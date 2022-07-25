package com.example.notesappcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notesappcompose.data.Notes
import com.example.notesappcompose.viewmodel.NotesViewModel

@Composable
fun AddEditNote(
    navController: NavHostController,
    addEditNotes: (Int, String, String) -> Unit,
    id: Int,
    notes: List<Notes>
) {

    var title by rememberSaveable { mutableStateOf(getTitle(id, notes)) }
    var description by rememberSaveable { mutableStateOf(getDescription(id, notes)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {

         OutlinedTextField(
             modifier = Modifier.fillMaxWidth(),
             value = title,
             onValueChange = { newText -> title  = newText },
             label = { Text("Title") },
         )

        Spacer(modifier = Modifier.height(20.dp))

         OutlinedTextField(
             value = description,
             onValueChange = { newText -> description = newText },
             label = { Text("Description") }
         )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (id >= 0) {
                addEditNotes(id, title, description)
            } else {
                addEditNotes(-1, title, description)
            }

            navController.navigate("listNotes")
        }) {
            if (id >= 0) {
                Text(text = "Edit Note")
            } else {
                Text(text = "Add Note")
            }

        }

        // Screen(addEditNotes)


    }

}

@Composable
fun Screen(addEditNotes: (Int, String, String) -> Unit) {
    Text("In side screen composable")
    addEditNotes(1, "", "")
}

fun getTitle(id: Int, notes: List<Notes>): String {
    return if (id >= 0) {
        var note = notes.find { it.id == id }
        note!!.title
    } else {
        ""
    }
}

fun getDescription(id: Int, notes: List<Notes>): String {
    return if (id >= 0) {
        var note = notes.find { it.id == id }
        note!!.description
    } else {
        ""
    }
}