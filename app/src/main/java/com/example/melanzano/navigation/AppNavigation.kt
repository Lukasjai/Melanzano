package com.example.melanzano.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.melanzano.screens.AddNoteScreen
import com.example.melanzano.screens.HomeScreen
import com.example.melanzano.screens.ClockScreen
import com.example.melanzano.screens.WorkDoneScreen
import com.example.melanzano.viewmodels.NoteViewModel
import com.example.melanzano.viewmodels.TimerViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()){
    //
    val noteViewModel: NoteViewModel = viewModel()
    val timerViewModel: TimerViewModel = viewModel()

    NavHost(navController = navController, startDestination = "homescreen"){
        composable(route = "homescreen") { HomeScreen(viewModel = noteViewModel) }

        composable(route = "addnotescreen") { AddNoteScreen(viewModel = noteViewModel) }

        composable(route = "clockscreen") { ClockScreen(viewModel = timerViewModel) }

        composable(route = "workdonescreen") { WorkDoneScreen(viewModel = noteViewModel) }
    }
}