package com.morfeu.smartpoint.dto

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class RegistrationPFDto (

    @get:NotEmpty(message = "NOME NÃO PODE SER VAZIO.")
    @get:Length(min = 3, max = 200, message = "NOME DEVE CONTER ENTRE 3 E 200 CARACTERES.")
    val name: String? = "",

    @get:NotEmpty(message = "EMAIL NÃO PODE SER VAZIO.")
    @get:Length(min = 5, max = 200, message = "EMAIL DEVE CONTER ENTRE 5 E 200 CARACTERES.")
    @get:Email(message = "EMAIL INVÁLIDO.")
    val email: String? = "",

    @get:NotEmpty(message = "SENHA NÃO PODE SER VAZIA.")
    val password: String? = "",

    @get:NotEmpty(message = "CPF NÃO PODE SER VAZIO.")
    @get:CPF(message = "CPF INVÁLIDO")
    val cpf: String? = "",

    @get:NotEmpty(message = "CNPJ NÃO PODE SER VAZIO.")
    @get:CNPJ(message = "CNPJ INVÁLIDO")
    val cnpj: String? = "",

    val companyId: String? = "",
    val hourlyValue: String? = null,
    val hoursWorkDay: String? = null,
    val lunchHours: String? = null,
    val id: String? = null
)