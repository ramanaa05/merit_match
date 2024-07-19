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
    @GET("/users/")
    suspend fun getUserDetails(@Query("username") username: String): User

    @POST("/users/")
    suspend fun createUser(@Body user: User): User
}