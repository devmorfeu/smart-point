package com.morfeu.smartpoint.service

import com.morfeu.smartpoint.entity.Company

interface ICompanyService {

    fun findByCnpj(cnpj: String): Company?

    fun persist(company: Company): Company
}