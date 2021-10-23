package com.morfeu.smartpoint.repository

import com.morfeu.smartpoint.entity.Launch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface ILaunchRepository : MongoRepository<Launch, String> {

    fun findByEmployeeId(employeeId: String, pageable: Pageable): Page<Launch>
}