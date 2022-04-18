package com.example.melanzano.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.melanzano.models.Note

class NoteViewModel : ViewModel() {
    private var notes = mutableStateListOf<Note>()

    init {
        notes.addAll(
            listOf(
                Note("Do MAD Learning Dirary 4", "23.03.2022 10:00"),
                Note("Do Datenbanken", "24.03.2022 11:32"),
            )
        )
    }

    fun addNote(note: Note){
        notes.add(note)
    }

    fun removeNote(note: Note){
        notes.remove(note)
    }

    fun getAllNotes(): List<Note>{
        return notes
    }

    fun sortNotes(){

    }

    fun filterNotes(){

    }
}