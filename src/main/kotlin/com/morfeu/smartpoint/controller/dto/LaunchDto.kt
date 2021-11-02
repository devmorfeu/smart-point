package com.morfeu.smartpoint.controller.dto

import javax.validation.constraints.NotEmpty

data class LaunchDto (

    @get:NotEmpty(message = "DATA NÃO PODE SER VAZIA.")
    val date: String? = null,

    @get:NotEmpty(message = "TIPO NÃO PODE SER VAZIO.")
    val type: String? = null,

    val description: String? = null,
    val localization: String? = null,
    val employeeId: String? = null,
    var id: String? = null
)