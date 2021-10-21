package com.morfeu.pontointeligente.service.impl

import com.morfeu.pontointeligente.entity.Company
import com.morfeu.pontointeligente.repository.ICompanyRepository
import com.morfeu.pontointeligente.service.ICompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl (val companyRepository: ICompanyRepository) : ICompanyService {

    override fun findByCnpj(cnpj: String): Company? = companyRepository.findByCnpj(cnpj)

    override fun persist(company: Company): Company = companyRepository.save(company)
}