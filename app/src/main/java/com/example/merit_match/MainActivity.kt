package com.example.merit_match

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.merit_match.ui.theme.Merit_matchTheme
val theUser = mutableStateOf(User("", "", "", 0))
val task_list = mutableStateListOf<Task>()
val task_list_all = mutableStateListOf<Task>()
val task_list_reserved = mutableStateListOf<Task>()
val task_available_display = mutableStateListOf<Task>()
val task_posted = mutableStateListOf<Task>()
val status_list = mutableStateListOf<ApprovalStatus>()
val status_id = mutableStateListOf<Int>()
val pageFlag = mutableStateOf(0)
val taskGlobal = mutableStateOf(Task(0, "", "", 0, ""))
val history = mutableStateListOf<History>()
val rateUser = mutableStateOf("")

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Merit_matchTheme {
                Navigation()
                if (pageFlag.value == 1){
                    Confirmation()
                }
                if (pageFlag.value == 2){
                    RatingPage()
                }
            }
        }
    }
}

