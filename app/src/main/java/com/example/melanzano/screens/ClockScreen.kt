package com.example.melanzano.screens

import android.support.v4.os.IResultReceiver
import android.widget.NumberPicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chillibits.composenumberpicker.HorizontalNumberPicker
import com.example.melanzano.viewmodels.NoteViewModel
import com.example.melanzano.viewmodels.TimerViewModel
import com.example.melanzano.widgets.AddNoteWidget
import com.example.melanzano.widgets.NoteCards
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.concurrent.timer

@Preview(showBackground = true)
@Composable
fun ClockScreen(viewModel: TimerViewModel = viewModel()) {

    val f: NumberFormat = DecimalFormat("00")

    var timerStarted by remember {
        mutableStateOf(false)
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 2.dp),
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

                if (!viewModel.timerStarted.value) {
                    viewModel.startTimeCounter()
                } else {
                    viewModel.reset()
                }

            }) {
                if (viewModel.timerStarted.value){
                    Text(text = "Reset")
                }
                else {
                    Text(text = "Start timer")
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {

            var context = LocalContext.current

            HorizontalNumberPicker(
                min = 10,
                max = 100,
                default = 50,
                modifier = Modifier.padding(10.dp),
                onValueChange = { value ->
                    Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
                }
            )
        }
    }


}