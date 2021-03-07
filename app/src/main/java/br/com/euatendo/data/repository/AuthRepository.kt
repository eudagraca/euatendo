package br.com.euatendo.data.repository

import br.com.euatendo.data.network.APIService

class AuthRepository(
    private val api: APIService
): BaseRepository() {

    suspend fun login(
        email: String,
        password: String,
    ) = safeApiCall{
        api.login(email, password)
    }
}