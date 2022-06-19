package com.example.melanzano.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            "Melanzano is an Application that will help you reach your goals no matter if you study have to finish things at work or learn sometihing for your own" +
                    "\n" +
                    "This App works with the Pomodoro principle. \n" +
                    "\n" +
                    "1. write down your to do´s\n" +
                    "\n" +
                    "2. set the timer (preferential to 20min)\n" +
                    "\n" +
                    "3. only focus on the to dos at the learn session, exile things that which are disruptive to you\n" +
                    "\n" +
                    "4. use the breaks to get your head free do things you like\n" +
                    "\n" +
                    "5. one iteration contains 4 learn sessions with 3 short breaks and one long break in the end\n" +
                    "\n" +
                    "6. do as much iterations as you can and comply your to dos\n"
        )

    }

}