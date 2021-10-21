package com.morfeu.pontointeligente.repository

import com.morfeu.pontointeligente.entity.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface IEmployeeRepository : MongoRepository<Employee, String> {

    fun findByEmail(email: String): Employee?

    fun findByCpf(cpf: String): Employee?
}