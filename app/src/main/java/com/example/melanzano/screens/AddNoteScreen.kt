package com.example.melanzano.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.melanzano.viewmodels.NoteViewModel
import com.example.melanzano.widgets.AddNoteWidget
import com.example.melanzano.widgets.NoteCards

@Composable
fun AddNoteScreen(viewModel: NoteViewModel = viewModel()){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        AddNoteWidget{ note ->
            viewModel.addNote(note)
        }

        Divider()

        NoteCards(
            notes = viewModel.getAllNotes(),
            trashcan = true,
        ) { note ->
            viewModel.removeNote(note)
        }
    }
}