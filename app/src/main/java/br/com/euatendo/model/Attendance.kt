package br.com.euatendo.model

import java.util.*

data class Attendance(
    val name: String,
    val motivation: String,
    val serviceTime: Int,
    val attendanceDate: Date,
    val canceledDate: Date,
    val createdAt: Date,
    val updatedAt: Date,
    val therapist: Therapist,
) {
}