package com.example.melanzano.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.melanzano.models.Note
import com.example.melanzano.viewmodels.NoteViewModel
import com.example.melanzano.widgets.NoteCards

@Composable
fun HomeScreen(viewModel: NoteViewModel = viewModel()){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Melanzano",
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.h5)
        Text(text = "Your To Do´s:")

        NoteCards(
            notes = viewModel.getAllNotes()
        ){ note ->
            viewModel.removeNote(note)
        }
    }
}
