package com.example.melanzano

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.melanzano.navigation.AppNavigation
import com.example.melanzano.ui.theme.MelanzanoTheme

class MainActivity : ComponentActivity() {

    override fun onPause() {
        super.onPause()
        Log.i("onPause", "this is onPause")

    }

    override fun onStart() {
        super.onStart()
        Log.i("onStart", "this is onStart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "this is on Destroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("onRestart", "this is onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume", "this is onResume")
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("onCreate", "this is onCreate")

        setContent {
            val navController = rememberNavController()

            MelanzanoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        ) {

                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home") },
                                selected = currentRoute == "homescreen",
                                onClick = {
                                    navController.navigate("homescreen")
                                },
                            )

                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "addnotescreen") },
                                selected = currentRoute == "addnotescreen",
                                onClick = {
                                    navController.navigate("addnotescreen")
                                },
                            )
                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.AddCircle, contentDescription = "clockscreen") },
                                selected = currentRoute == "clockscreen",
                                onClick = {
                                    navController.navigate("clockscreen")
                                },
                            )
                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Default.Done, contentDescription = "pausescreen") },
                                selected = currentRoute == "pausescreen",
                                onClick = {
                                    navController.navigate("pausescreen")
                                },
                            )
                        }
                    }
                ) { innerPadding -> // needed so that the bottom bar does not overlap screen contents
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation(navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MelanzanoTheme {
        AppNavigation()
    }
}