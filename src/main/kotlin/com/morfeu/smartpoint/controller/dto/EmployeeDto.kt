package com.morfeu.smartpoint.controller.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class EmployeeDto (

    @get:NotEmpty(message = "NOME NÃO PODE SER VAZIO.")
    @get:Length(min = 3, max = 200, message = "NOME DEVE CONTER ENTRE 3 E 200 CARACTERES.")
    val name: String = "",

    @get:NotEmpty(message = "EMAIL NÃO PODE SER VAZIO.")
    @get:Length(min = 5, max = 200, message = "EMAIL DEVE CONTER ENTRE 5 E 200 CARACTERES.")
    @get:Email(message = "EMAIL INVÁLIDO.")
    val email: String = "",

    val password: String = "",
    val hourlyValue: String? = null,
    val hoursWorkDay: String? = null,
    val lunchHours: String? = null,
    val id: String? = null
)