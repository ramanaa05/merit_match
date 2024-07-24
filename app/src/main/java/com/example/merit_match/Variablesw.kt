package com.example.merit_match

data class User(
    var username: String,
    var password: String,
    var email: String,
    var karmaPoints: Int
)

data class Task(
    val id: Int,
    val task: String,
    val username: String,
    val karmaPoints: Int,
    val reserved: String
)

data class Tasks(
    val tasks: List<Task>
)

data class ApprovalStatus(
    val id: Int,
    val approved: Boolean
)

data class StatusList(
    val status: List<ApprovalStatus>
)