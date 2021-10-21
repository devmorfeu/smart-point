package com.morfeu.pontointeligente.repository

import com.morfeu.pontointeligente.entity.Launch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface ILaunchRepository : MongoRepository<Launch, String> {

    fun findByEmployeeId(employeeId: String, pageable: Pageable): Page<Launch>
}