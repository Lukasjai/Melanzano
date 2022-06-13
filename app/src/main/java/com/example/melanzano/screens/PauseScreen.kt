package com.example.melanzano.screens


/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.melanzano.R
import com.example.melanzano.screens.HMSFontInfo.Companion.HMS
import com.example.melanzano.screens.HMSFontInfo.Companion.MS
import com.example.melanzano.screens.HMSFontInfo.Companion.S
import com.example.melanzano.ui.theme.Purple200
import com.example.melanzano.ui.theme.Teal200
import com.example.melanzano.viewmodels.TimerViewModel

@Composable
fun PauseScreen(
    timeInSec: Int,
    viewModel: TimerViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {

    var trigger by remember { mutableStateOf(timeInSec) }

    val elapsed by animateIntAsState(
        targetValue = trigger * 1000,
        animationSpec = tween(timeInSec * 1000, easing = LinearEasing)
    )

    DisposableEffect(Unit) {
        trigger = 0
        onDispose { }
    }

    Column(
        Modifier
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Box {
            AnimationElapsedTime(elapsed)
            AnimationCircleCanvas(elapsed)
        }

        Spacer(modifier = Modifier.size(55.dp))

        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(70.dp)
                .shadow(30.dp, shape = CircleShape)
                .clickable { navController.navigate("homescreen" )},
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
    }
}

@Composable
private fun BoxScope.AnimationElapsedTime(elapsed: Int) {

    val (hou, min, sec) = remember(elapsed / 1000) {
        val elapsedInSec = elapsed / 1000
        val hou = elapsedInSec / 3600
        val min = elapsedInSec / 60 - hou * 60
        val sec = elapsedInSec % 60
        Triple(hou, min, sec)
    }

    val mills = remember(elapsed) {
        elapsed % 1000
    }

    val onlySec = remember(hou, min) {
        hou == 0 && min == 0
    }

    val transition = rememberInfiniteTransition()

    val animatedFont by transition.animateFloat(
        initialValue = 1.5f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(tween(60000), RepeatMode.Reverse)
    )

    val (size, labelSize, padding) = when {
        hou > 0 -> HMS
        min > 0 -> MS
        else -> S
    }

    Row(
        Modifier
            .align(Alignment.Center)
            .padding(start = padding, end = padding, top = 10.dp, bottom = 10.dp)
    ) {
        if (hou > 0) {
            DisplayTime(
                hou.formatTime(),
                "h",
                fontSize = size,
                labelSize = labelSize
            )
        }
        if (min > 0) {
            DisplayTime(
                min.formatTime(),
                "m",
                fontSize = size,
                labelSize = labelSize
            )
        }
        DisplayTime(
            if (onlySec) sec.toString() else sec.formatTime(),
            if (onlySec) "" else "s",
            fontSize = size * (if (onlySec && sec < 10 && mills != 0) animatedFont else 1f),
            labelSize = labelSize,
            textAlign = if (onlySec) TextAlign.Center else TextAlign.End
        )
    }

    Text(
        ".${(mills / 10).formatTime()}",
        Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 80.dp),
        fontSize = 30.sp,
        fontFamily = FontFamily.Cursive,
        style = typography.subtitle1,
        color = MaterialTheme.colors.primary,
    )
}

@Composable
private fun BoxScope.AnimationCircleCanvas(durationMills: Int) {
    val transition = rememberInfiniteTransition()
    var trigger by remember { mutableStateOf(0f) }
    var isFinished by remember { mutableStateOf(false) }

    val animateTween by animateFloatAsState(
        targetValue = trigger,
        animationSpec = tween(
            durationMillis = durationMills,
            easing = LinearEasing
        ),
        finishedListener = {
            isFinished = true
        }
    )

    val animatedRestart by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Restart)
    )
    val animatedReverse by transition.animateFloat(
        initialValue = 1.05f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )

    DisposableEffect(Unit) {
        trigger = 360f
        onDispose {}
    }

    val strokeRestart = Stroke(15f)
    val strokeReverse = Stroke(10f)
    val color = MaterialTheme.colors.primary
    val secondColor = MaterialTheme.colors.secondary
    Canvas(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp)
            .size(350.dp)
    ) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val topLeftOffset = Offset(10f, 10f)
        val size = Size(radius * 2, radius * 2)

        if (!isFinished) {

            drawArc(
                color = color,
                startAngle = animatedRestart,
                sweepAngle = 150f,
                topLeft = topLeftOffset,
                size = size,
                useCenter = false,
                style = strokeRestart,
            )
        }

        drawCircle(
            color = secondColor,
            style = strokeReverse,
            radius = radius * if (isFinished) 1f else animatedReverse
        )

        drawArc(
            startAngle = 270f,
            sweepAngle = animateTween,
            brush = Brush.radialGradient(
                radius = radius,
                colors = listOf(
                    Purple200.copy(0.3f),
                    Teal200.copy(0.2f),
                    Color.White.copy(0.3f)
                ),
            ),
            useCenter = true,
            style = Fill,
        )
    }
}

@Preview
@Composable
fun DisplayPreview() {
    PauseScreen(1000)
}

private fun Int.formatTime() = String.format("%02d", this)

private data class HMSFontInfo(val fontSize: TextUnit, val labelSize: TextUnit, val padding: Dp) {
    companion object {
        val HMS = HMSFontInfo(50.sp, 20.sp, 40.dp)
        val MS = HMSFontInfo(85.sp, 30.sp, 50.dp)
        val S = HMSFontInfo(150.sp, 50.sp, 55.dp)
    }
}
@Composable
fun RowScope.DisplayTime(
    num: String,
    label: String = "",
    heightLight: Boolean = true,
    fontSize: TextUnit = 50.sp,
    labelSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.End
) {

    val textColor = if (heightLight) MaterialTheme.colors.primary
    else Color.Unspecified.copy(alpha = 0.5f)

    Text(
        num,
        Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = FontFamily.Cursive,
        color = textColor,
        style = typography.subtitle1
    )
    if (label.isNotEmpty()) {
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterVertically),
            text = label,
            fontSize = labelSize,
            color = textColor
        )
        Spacer(modifier = Modifier.width(15.dp))
    }
}
