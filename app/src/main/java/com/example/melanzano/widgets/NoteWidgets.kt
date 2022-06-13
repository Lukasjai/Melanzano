package com.example.melanzano.widgets

import android.util.Log
import android.widget.CheckBox
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melanzano.models.Note
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NoteCard(
    note: Note,
    trashcan: Boolean,
    boxVisible: Boolean,
    onBoxTick: (Note) -> Unit = {},
    onDeleteClick: (Note) -> Unit = {}
) {
    val checkedState = remember { mutableStateOf(note.checked) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {

        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (boxVisible){
                    Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it
                        note.checked = checkedState.value
                        Log.i("info", "note.checked: " + note.checked.toString())
                        Log.i("info", "checkedStateRememberShit: " + checkedState.value)

                        onBoxTick(note)
                        },
                    modifier = Modifier.absolutePadding(0.dp, 0.dp,10.dp, 0.dp)
                    )
                }
                Text(
                    text = note.text,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.width(250.dp)
                )
                if (trashcan){
                    IconButton(onClick = {
                    onDeleteClick(note)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "remove note")
                }}

            }
            Text(
                text = note.date,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun NoteCards(
    notes: List<Note> = listOf(),
    trashcan: Boolean,
    boxVisible: Boolean,
    onBoxTick: (Note) -> Unit = {},
    onDeleteClick: (Note) -> Unit = {}
) {
    LazyColumn {
        items(notes) { note ->
            NoteCard(note,
                trashcan,
                boxVisible,
            onBoxTick= {note ->
                onBoxTick(note)}) {
                    note ->
                onDeleteClick(note)
            }
        }
    }
}

@Composable
fun AddNoteWidget(
    onSaveClick: (Note) -> Unit = {}
) {
    Text(
        text = "Add a ToDo",
        style = MaterialTheme.typography.h5,
        color = Color.Black
    )

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { value -> text = value },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.LightGray
        ),
        label = { Text(text = "Note", color = Color.Black) }
    )

    Button(
        modifier = Modifier
            .padding(20.dp)
            .width(200.dp)
            .height(50.dp),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        onClick = {
            if (text.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
                val currentDate = sdf.format(Date())
                val newNote = Note(text, currentDate, false)

                Log.d("AddNoteScreen", "added ${newNote.text} ${newNote.date}")

                onSaveClick(newNote)

                text = ""
            }

        }) {

        Text(text = "Save")
    }
}

