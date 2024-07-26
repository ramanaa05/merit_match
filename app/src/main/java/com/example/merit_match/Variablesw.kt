package com.example.merit_match

data class User(
    var username: String,
    var password: String,
    var email: String,
    var karmaPoints: Int,
    var rating: Int,
    var ratingTotal: Int
)

data class Task(
    val id: Int,
    var task: String,
    val username: String,
    var karmaPoints: Int,
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

data class History(
    val id: Int,
    var task: String,
    val username: String,
    var karmaPoints: Int,
    val reserved: String,
    var status: String
)

data class HistoryList(
    val history: List<History>
)

data class RatingUser(
    val rating: Int
)