package com.example.melanzano.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ExplanationScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "What is Melanzano?",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h4
            )
        }
        Text(
            text =
            "Melanzano is an application that will help you reach your goals no matter if you study, have to finish things at work, or learn something for your own" +
                    "\n" +
                    "This App works with the Pomodoro principle. \n" +
                    "\n" +
                    "1. Write down your ToDos\n" +
                    "\n" +
                    "2. Set the timer (preferentially to 20min)\n" +
                    "\n" +
                    "3. Only focus on the ToDos during the learn session, exile things that are disruptive to you\n" +
                    "\n" +
                    "4. Use the breaks to get your head free. Do things you like!\n" +
                    "\n" +
                    "5. One iteration contains 4 learn sessions with 3 short breaks and one long break in the end\n" +
                    "\n" +
                    "6. Do as much iterations as you can and complete your ToDos\n",
            textAlign = TextAlign.Justify
        )

    }

}