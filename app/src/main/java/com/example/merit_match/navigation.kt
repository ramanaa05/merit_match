package com.example.merit_match

import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginPage.route){
        composable(
            route = Screen.LoginPage.route
        ){
            LoginPage(navController)
        }
        composable(
            route = Screen.HomePage.route
        ){
            BackHandler(true){
                Log.i("LOG_TAG", "Clicked back")
            }
            HomePage(navController)
        }
        composable(
            route = Screen.CreatePage.route
        ){
            CreatePage(navController)
        }
    }
}