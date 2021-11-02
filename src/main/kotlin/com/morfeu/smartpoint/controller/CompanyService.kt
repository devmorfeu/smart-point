package com.morfeu.smartpoint.controller

import com.morfeu.smartpoint.controller.dto.CompanyDto
import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.response.Response
import com.morfeu.smartpoint.service.ICompanyService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empresas")
class CompanyService(val iCompanyService: ICompanyService) {

    @GetMapping("cnpj/{cnpj}")
    fun findByCnpj(@PathVariable("cnpj") cnpj: String): ResponseEntity<Response<CompanyDto>> {

        val response: Response<CompanyDto> = Response()
        val company: Company? = iCompanyService.findByCnpj(cnpj)

        if (company == null) {
            response.errors.add("Empresa não encontrada para o CNPJ $cnpj")
            return badRequest().body(response)
        }

        response.data = entityToDto(company)
        return ok(response)
    }

    private fun entityToDto(company: Company): CompanyDto = CompanyDto(company.socialReason, company.cnpj, company.id)
}