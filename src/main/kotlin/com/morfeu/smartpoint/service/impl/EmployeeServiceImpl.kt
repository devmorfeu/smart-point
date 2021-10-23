package com.morfeu.smartpoint.service.impl

import com.morfeu.smartpoint.service.IEmployeeService
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.repository.IEmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(val iEmployeeRepository: IEmployeeRepository) : IEmployeeService {

    override fun persist(employee: Employee): Employee = iEmployeeRepository.save(employee)


    override fun findByCpf(cpf: String): Employee? = iEmployeeRepository.findByCpf(cpf);


    override fun findByEmail(email: String): Employee? = iEmployeeRepository.findByEmail(email)


    override fun findById(id: String): Employee? = iEmployeeRepository.findById(id).get()

}