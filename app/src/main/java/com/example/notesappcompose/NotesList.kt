package com.example.notesappcompose

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notesappcompose.data.model.Notes
import kotlinx.coroutines.flow.StateFlow


@Composable
fun NotesList(
    notes: StateFlow<List<Notes>>,
    navController: NavHostController,
    deleteNote: (Notes) -> Unit
) {

    val notes = notes.collectAsState().value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        items(items = notes, key = { note ->
            note.id as Any
        }) { note ->
            NoteCard(note, navController, deleteNote)
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}

@Composable
fun NoteCard(note: Notes, navController: NavHostController, deleteNote: (Notes) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = Color(0xFFF7C2D4),
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Column(modifier = Modifier.weight(0.8f)) {
                Text(text = note.title)
                Text(text = note.description)
            }
            IconButton(
                onClick = {
                    val id = note.id
                    navController.navigate("edit/$id")
                }, modifier = Modifier
                    .weight(0.1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                    contentDescription = "Edit"
                )
            }
            IconButton(
                onClick = {
                    deleteNote(note)
                    Toast.makeText(context, "Record deleted", Toast.LENGTH_LONG).show()
                }, modifier = Modifier
                    .weight(0.1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                    contentDescription = "Delete"
                )
            }

        }

    }
}

/*fun getNotes(activity: ComponentActivity) {
    activity.lifecycleScope.launch {
        activity.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            userViewModel.doGetUserDetails()
            userViewModel.userDetails.collect { users ->

                for (user in users) {
                    //set data into view
                    binding.txtName.text = user.name
                    binding.txtAge.text = user.age
                    binding.txtNumber.text = user.number
                }

            }
        }
    }
}*/


