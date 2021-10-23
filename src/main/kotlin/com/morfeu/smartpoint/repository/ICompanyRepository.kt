package com.morfeu.smartpoint.repository

import com.morfeu.smartpoint.entity.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface ICompanyRepository : MongoRepository<Company, String>{

    fun findByCnpj(cnpj: String) : Company?
}