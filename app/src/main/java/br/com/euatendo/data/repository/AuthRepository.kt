package br.com.euatendo.data.repository

import br.com.euatendo.data.network.APIService
import br.com.euatendo.model.User

class AuthRepository(
    private val api: APIService
): BaseRepository() {

    suspend fun login(
        email: String,
        password: String,
    ) = safeApiCall{
        api.login(User(email = email, password = password))
    }
}