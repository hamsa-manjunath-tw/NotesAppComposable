package com.example.notesappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesappcompose.ui.theme.NotesAppComposeTheme
import com.example.notesappcompose.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notesViewModel by viewModels<NotesViewModel>()
            val navController = rememberNavController()

            NotesAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
        NavigationComponent(navController = navController, notesViewModel)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        IconButton(onClick = { navController.navigate("add") }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "Add" )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, notesViewModel: NotesViewModel){

    NavHost(navController = navController, startDestination = "listNotes"){
        composable(route = "listNotes"){
            NotesList(navController = navController, notes = notesViewModel.notesList,
                deleteNote = notesViewModel::deleteNote
            )
        }
        composable(route = "add"){
            AddEditNote(navController, notesViewModel::addEditNotes, -1, notesViewModel.notesList)
        }

        composable(route = "edit/{id}", arguments = listOf(navArgument("id"){
            type= NavType.IntType

        })){
                backStackEntry ->
            backStackEntry.arguments?.getInt("id")
                ?.let { AddEditNote(navController,notesViewModel::addEditNotes, it, notesViewModel.notesList) }
           /* val id = it.arguments?.getInt("id")
            if (id != null) {
                AddEditNote(navController, notesViewModel, id.toInt())
            }*/
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppComposeTheme {
    }
}