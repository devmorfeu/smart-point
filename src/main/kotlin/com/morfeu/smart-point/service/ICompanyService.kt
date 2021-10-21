package com.morfeu.pontointeligente.service

import com.morfeu.pontointeligente.entity.Company

interface ICompanyService {

    fun findByCnpj(cnpj: String): Company?

    fun persist(company: Company): Company
}