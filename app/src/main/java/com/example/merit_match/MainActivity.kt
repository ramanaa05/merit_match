package com.example.merit_match

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.merit_match.ui.theme.Merit_matchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Merit_matchTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun UserInfoScreen(viewModel: MainViewModel) {
    val state = viewModel.infoState.value
    viewModel.fetchUser("ramanaa")

    var loading by remember {
        mutableStateOf(viewModel.infoState.value.loading)
    }
    var a by remember {
        mutableStateOf(true)
    }

    if (state.error != null) {
        // Show error message
        Text(text = state.error)
    }
    else {
        // Show user information
        Column {
            Text(text = loading.toString())
            Text(text = "Username: ${state.user.username}")
            Text(text = "Email: ${state.user.email}")
            Text(text = "Karma Points: ${state.user.karmaPoints}")
        }
    }

    LaunchedEffect(key1 = a) {
        loading = viewModel.infoState.value.loading
        a = !a
    }
}