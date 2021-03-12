package br.com.euatendo.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("createdAt")
    val created_at: Date? = null,
    @SerializedName("updatedAt")
    val updated_at: Date? = null) {
}