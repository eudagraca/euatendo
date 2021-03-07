package br.com.euatendo.data.network

import br.com.euatendo.model.User
import br.com.euatendo.data.responses.LoginResponse
import retrofit2.http.*

interface APIService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int) : User

    @FormUrlEncoded
    @POST("users")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}