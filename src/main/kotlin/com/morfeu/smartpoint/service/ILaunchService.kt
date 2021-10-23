package com.morfeu.smartpoint.service

import com.morfeu.smartpoint.entity.Launch
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ILaunchService {

    fun findEmployeeById(employeeId: String, pageRequest: PageRequest): Page<Launch>

    fun findById(id: String): Launch?

    fun persist(launch: Launch): Launch

    fun remove(id: String)
}