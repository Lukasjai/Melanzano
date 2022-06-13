package com.example.melanzano.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chillibits.composenumberpicker.HorizontalNumberPicker
import com.example.melanzano.viewmodels.TimerViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

@Preview(showBackground = true)
@Composable
fun ClockScreen(viewModel: TimerViewModel = viewModel()) {

    val f: NumberFormat = DecimalFormat("00")

    val TIMER_DEFAULT_Sec = 5

    var userTime by remember{
        mutableStateOf(0)
    }

    var timerSec by remember {
        mutableStateOf(TIMER_DEFAULT_Sec)
    }

    val TIMER_DEFAULT_Min = 0

    var timerMin by remember {
        mutableStateOf(TIMER_DEFAULT_Min)
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically)
        {
            when {
                viewModel.timerStarted.value -> {
                    Text(
                        text = "Start working!",
                        style = typography.caption,
                        fontSize = 40.sp
                    )
                }
                viewModel.pauseTimerStarted.value -> {
                    Text(
                        text = "Pause :)",
                        style = typography.caption,
                        fontSize = 40.sp
                    )
                }
                else -> {
                    Text(
                        text = "  ",
                        style = typography.caption,
                        fontSize = 40.sp
                    )
                }
            }
            
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 2.dp)
                .border(1.dp, Color.Black, shape = RectangleShape),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "00",
                fontSize = 40.sp,
                color = Color.Black,
                style = typography.caption
            )

            Text(text = ":",
                fontSize = 30.sp,
                color = Color.Black,
                style = typography.caption,
            )

            Text(
                text = f.format((viewModel.timeInSeconds.value / 60).toInt()),
                fontSize = 40.sp,
                color = Color.Black,
                style = typography.caption
            )

            Text(text = ":",
                fontSize = 30.sp,
                color = Color.Black,
                style = typography.caption)
            
            Text(
                text = f.format((viewModel.timeInSeconds.value % 60)),
                fontSize = 40.sp,
                color = Color.Black,
                style = typography.caption
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {

                if (!viewModel.timerStarted.value && !viewModel.pauseTimerStarted.value) {
                    viewModel.startTimeCounter()
                } else {
                    userTime = timerSec + (timerMin * 60)
                    viewModel.userTime = userTime
                    viewModel.reset((timerSec +(timerMin *60)))
                }

            }) {
                if (viewModel.timerStarted.value || viewModel.pauseTimerStarted.value){
                    Text(text = "Reset")
                }
                else {
                    Text(text = "Start timer")
                }
            }
        }
        Divider(modifier = Modifier.padding(40.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            HorizontalNumberPicker(
                min = 5,
                max = 59,
                default = TIMER_DEFAULT_Sec,
                modifier = Modifier.padding(10.dp),
                onValueChange = { value ->
                    timerSec = value
                },
            )
            Text(text = "seconds", fontSize = 30.sp)
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            HorizontalNumberPicker(
                min = 0,
                max = 59,
                default = TIMER_DEFAULT_Min,
                modifier = Modifier.padding(10.dp),
                onValueChange = { value ->
                    timerMin = value
                },
            )
            Text(text = "minutes", fontSize = 30.sp)
        }
    }


}