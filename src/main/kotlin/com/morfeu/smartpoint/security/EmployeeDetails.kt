package com.morfeu.smartpoint.security

import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.service.IEmployeeService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class EmployeeDetails(val iEmployeeService: IEmployeeService): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username != null) {
            val employee: Employee? = iEmployeeService.findByEmail(username)
            if (employee != null) {
                return MainEmployee(employee)
            }
        }
        throw UsernameNotFoundException(username)
    }
}