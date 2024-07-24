package com.example.merit_match

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


private val retrofit = Retrofit
    .Builder()
    .baseUrl("http://127.0.0.1:8000/") // Ensure this IP is correct for your environment
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: ApiServices = retrofit.create(ApiServices::class.java)

interface ApiServices {
    @POST("/users/authentication")
    suspend fun getUserDetails(@Body user: User): User

    @POST("/users/")
    suspend fun createUser(@Body user: User): User

    @GET("/tasks/")
    suspend fun getTasks(@Query("username") username: String): Tasks

    @POST("/tasks/")
    suspend fun createTask(@Body task: Task): Task

    @GET("/tasks/all")
    suspend fun getAllTasks(): Tasks

    @GET("/tasks/reserved/")
    suspend fun getReservedTasks(@Query("username") username: String): Tasks

    @POST("/tasks/reserve/")
    suspend fun reserve(@Body task: Task): Task

    @POST("/tasks/approve/")
    suspend fun approveTask(@Body status: ApprovalStatus): ApprovalStatus

    @GET("/tasks/approve/all")
    suspend fun getAllStatus(): StatusList

    @POST("/tasks/all/delete")
    suspend fun deleteTask(@Body task: Task): Task

    @POST("/tasks/approve/delete")
    suspend fun deleteApproval(@Body status: ApprovalStatus): ApprovalStatus

    @POST("/tasks/transaction/")
    suspend fun transaction(@Body task: Task): Task
}