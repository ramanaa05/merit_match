package com.example.merit_match

import android.graphics.BitmapFactory
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream


class MainViewModel : ViewModel() {
    private val _infoState = mutableStateOf(InfoState())
    val infoState: State<InfoState> = _infoState

    fun fetchUser(username: String) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.getUserDetails(username)
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    user = response,
                    error = null
                )
            } catch (e: Exception) {
                // Handle error
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }
    fun createUser(user: User) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.createUser(user)
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    user = response,
                    error = null
                )
            } catch (e: Exception) {
                // Handle error
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    // InfoState data class to hold the state of user information
    data class InfoState(
        val loading: Boolean = false,
        val user: User = User("", "", "", 0),
        val error: String? = null
    )
}