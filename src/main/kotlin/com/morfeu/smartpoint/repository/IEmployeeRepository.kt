package com.morfeu.smartpoint.repository

import com.morfeu.smartpoint.entity.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface IEmployeeRepository : MongoRepository<Employee, String> {

    fun findByEmail(email: String): Employee?

    fun findByCpf(cpf: String): Employee?
}