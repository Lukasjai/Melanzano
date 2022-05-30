package com.example.melanzano.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.melanzano.viewmodels.NoteViewModel
import com.example.melanzano.widgets.NoteCards

@Composable
fun WorkDoneScreen(viewModel: NoteViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Work Done",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h4
            )

            //adds our favourite vegetable and trademarks sign :)
            AsyncImage(
                model = "https://cdn.pixabay.com/photo/2020/03/28/17/01/eggplant-4977808_960_720.png",
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }

        Text(text = "Your done Tasks:")

        NoteCards(
            notes = viewModel.getAllDoneTasks()
        ) { note ->
            viewModel.removeNote(note)
        }
    }
}
