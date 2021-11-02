package com.morfeu.smartpoint.controller

import com.morfeu.smartpoint.controller.mapper.PhysicalPersonRegistrationMapper
import com.morfeu.smartpoint.controller.dto.RegistrationPFDto
import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.response.Response
import com.morfeu.smartpoint.service.ICompanyService
import com.morfeu.smartpoint.service.IEmployeeService
import com.morfeu.smartpoint.util.ValidatorUtil
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/cadastrar-pf")
class PhysicalPersonRegistrationController(
    val iCompanyService: ICompanyService,
    val iEmployeeService: IEmployeeService,
    val validatorUtil: ValidatorUtil,
    val mapper: PhysicalPersonRegistrationMapper) {

    @PostMapping
    fun register(@Valid @RequestBody registrationPFDto: RegistrationPFDto, result: BindingResult): ResponseEntity<Response<RegistrationPFDto>> {

        val response: Response<RegistrationPFDto> = Response()

        val company: Company? = iCompanyService.findByCnpj(registrationPFDto.cnpj)
        validatorUtil.validateExistingDataPF(registrationPFDto, company, result)

        if (result.hasErrors()){
            for (erro in result.allErrors) response.errors.add(erro.defaultMessage!!)
            return badRequest().body(response)
        }

        val employee: Employee = mapper.dtoToEmployeeEntity(registrationPFDto, company!!)

        iEmployeeService.persist(employee)
        response.data = mapper.entityToDto(employee, company)

        return status(CREATED).body(response)
    }
}