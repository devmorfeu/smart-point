package com.morfeu.smartpoint.service.impl

import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.repository.ICompanyRepository
import com.morfeu.smartpoint.service.ICompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl (val companyRepository: ICompanyRepository) : ICompanyService {

    override fun findByCnpj(cnpj: String): Company? = companyRepository.findByCnpj(cnpj)

    override fun persist(company: Company): Company = companyRepository.save(company)
}