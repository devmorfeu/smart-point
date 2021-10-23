package com.morfeu.smartpoint.service

import com.morfeu.smartpoint.entity.Employee

interface IEmployeeService {

    fun persist(employee: Employee): Employee

    fun findByCpf(cpf: String): Employee?

    fun findByEmail(email: String): Employee?

    fun findById(id: String): Employee?
}