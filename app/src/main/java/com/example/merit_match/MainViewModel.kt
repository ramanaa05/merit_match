package com.example.merit_match

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _infoState = mutableStateOf(InfoState())
    val infoState: State<InfoState> = _infoState

    fun fetchUser(user: User) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.getUserDetails(user)
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

    fun fetchTasks(username: String) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.getTasks(username)
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    tasks = response,
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

    fun fetchReservedTasks(username: String) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.getReservedTasks(username)
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    reservedTasks = response,
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

    fun fetchAllTasks() {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.getAllTasks()
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    allTasks = response,
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

    fun fetchAllStatus() {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.getAllStatus()
                _infoState.value = _infoState.value.copy(
                    loading = false,
                    statusList = response,
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

    fun createTask(task: Task) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.createTask(task)
                _infoState.value = _infoState.value.copy(
                    loading = false,
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

    fun reserveTask(task: Task) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.reserve(task)
                _infoState.value = _infoState.value.copy(
                    loading = false,
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

    fun transaction(task: Task) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.transaction(task)
                _infoState.value = _infoState.value.copy(
                    loading = false,
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

    fun approveTask(status: ApprovalStatus) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.approveTask(status)
                _infoState.value = _infoState.value.copy(
                    loading = false,
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

    fun deleteTask(task: Task) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.deleteTask(task)
                _infoState.value = _infoState.value.copy(
                    loading = false,
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

    fun deleteApproval(status: ApprovalStatus) {
        infoState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                // Fetch user details
                val response = apiService.deleteApproval(status)
                _infoState.value = _infoState.value.copy(
                    loading = false,
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
        val tasks: Tasks = Tasks(emptyList()),
        val allTasks: Tasks = Tasks(emptyList()),
        val reservedTasks: Tasks = Tasks(emptyList()),
        val statusList: StatusList = StatusList(emptyList()),
        val error: String? = null
    )
}