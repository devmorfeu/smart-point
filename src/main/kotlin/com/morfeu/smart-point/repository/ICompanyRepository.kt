package com.morfeu.pontointeligente.repository

import com.morfeu.pontointeligente.entity.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface ICompanyRepository : MongoRepository<Company, String>{

    fun findByCnpj(cnpj: String) : Company?
}