package com.example.merit_match

data class User(
    val username: String,
    val password: String,
    val email: String,
    val karmaPoints: Int
)

data class Task(
    val id: Int,
    val task: String,
    val username: String,
    val karmaPoints: Int
)

data class Tasks(
    val tasks: List<Task>
)