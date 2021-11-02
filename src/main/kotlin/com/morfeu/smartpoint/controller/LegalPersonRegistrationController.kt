package com.morfeu.smartpoint.controller

import com.morfeu.smartpoint.controller.mapper.LegalPersonRegistrationMapper
import com.morfeu.smartpoint.controller.dto.RegistrationPJDto
import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.response.Response
import com.morfeu.smartpoint.service.ICompanyService
import com.morfeu.smartpoint.service.IEmployeeService
import com.morfeu.smartpoint.util.ValidatorUtil
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.status
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/cadastrar-pj")
class LegalPersonRegistrationController(
    val iCompanyService: ICompanyService,
    val iEmployeeService: IEmployeeService,
    val legalPersonRegistrationMapper: LegalPersonRegistrationMapper,
    val validatorUtil: ValidatorUtil) {

    @PostMapping
    fun register(@Valid @RequestBody registrationPJDto: RegistrationPJDto, result: BindingResult): ResponseEntity<Response<RegistrationPJDto>> {

        val response: Response<RegistrationPJDto> = Response()

        validatorUtil.validateExistingDataPJ(registrationPJDto, result)

        if (result.hasErrors()){
            for (erro in result.allErrors) response.errors.add(erro.defaultMessage!!)
            return badRequest().body(response)
        }

        val company: Company = legalPersonRegistrationMapper.dtoToCompanyEntity(registrationPJDto)
        iCompanyService.persist(company)

        val employee: Employee = legalPersonRegistrationMapper.dtoToEmployeeEntity(registrationPJDto, company)
        iEmployeeService.persist(employee)
        response.data = legalPersonRegistrationMapper.entityToDto(employee, company)

        return status(CREATED).body(response)
    }
}