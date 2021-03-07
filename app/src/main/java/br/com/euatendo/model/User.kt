package br.com.euatendo.model

import java.util.*

data class User(val name: String, val email: String,
                val password: String, val created_at: Date,
                val updated_at: Date) {
}