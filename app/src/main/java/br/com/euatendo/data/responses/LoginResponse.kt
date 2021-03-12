package br.com.euatendo.data.responses

import br.com.euatendo.model.User

data class LoginResponse (
    val user: User,
    val message: String,
    val token: String,
    val status: Int
    )