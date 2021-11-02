package com.morfeu.smartpoint.util

import com.morfeu.smartpoint.controller.dto.LaunchDto
import com.morfeu.smartpoint.controller.dto.RegistrationPFDto
import com.morfeu.smartpoint.controller.dto.RegistrationPJDto
import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.service.ICompanyService
import com.morfeu.smartpoint.service.IEmployeeService
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

@Component
class ValidatorUtil (val iEmployeeService: IEmployeeService, val iCompanyService: ICompanyService) {

     fun employeeValid(launchDto: LaunchDto, result: BindingResult) {

        if (launchDto.employeeId == null) {
            result.addError(ObjectError("employee", "FUNCIONÁRIO NÃO INFORMADO"))
            return
        }

        val employee: Employee? = iEmployeeService.findById(launchDto.employeeId)

        if (employee == null) {
            result.addError(ObjectError("employee", "FUNCIONÁRIO NÃO ENCONTRADO"))
        }
    }

    fun validateExistingDataPJ(registrationPJDto: RegistrationPJDto, result: BindingResult) {

        val company: Company? = iCompanyService.findByCnpj(registrationPJDto.cnpj)
        if (company != null) {
            result.addError(ObjectError("empresa", "Empresa já existente."))
        }

        val employeeCpf: Employee? = iEmployeeService.findByCpf(registrationPJDto.cpf)
        if (employeeCpf != null) {
            result.addError(ObjectError("funcionario", "CPF já existente."))
        }

        val employeeEmail: Employee? = iEmployeeService.findByEmail(registrationPJDto.email)
        if (employeeEmail != null) {
            result.addError(ObjectError("funcionario", "Email já existente."))
        }
    }

    fun validateExistingDataPF(registrationPFDto: RegistrationPFDto, company: Company?, result: BindingResult) {

        if (company != null) {
            result.addError(ObjectError("empresa", "Empresa não cadastrada."))
        }

        val employeeCpf: Employee? = iEmployeeService.findByCpf(registrationPFDto.cpf)
        if (employeeCpf != null) {
            result.addError(ObjectError("funcionario", "CPF já existente."))
        }

        val employeeEmail: Employee? = iEmployeeService.findByEmail(registrationPFDto.email)
        if (employeeEmail != null) {
            result.addError(ObjectError("funcionario", "Email já existente."))
        }
    }
}