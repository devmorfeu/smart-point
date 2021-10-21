package com.morfeu.pontointeligente.entity

import com.morfeu.pontointeligente.enum.ProfileEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Employee (
    val name: String,
    val email: String,
    val password: String,
    val cpf: String,
    val profile: ProfileEnum,
    val companyId: String,
    val hourlyValue: Double? = 0.0,
    val hoursWorkDay: Float? = 0.0f,
    val lunchHours: Float? = 0.0f,
    @Id val id: String? = null
)