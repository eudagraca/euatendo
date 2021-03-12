package br.com.euatendo.data.network

import br.com.euatendo.model.User
import br.com.euatendo.data.responses.LoginResponse
import retrofit2.http.*

interface APIService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int) : User

    @POST("login")
    suspend fun login(
        @Body user: User
    ): LoginResponse
}