package com.morfeu.smartpoint.controller.mapper

import com.morfeu.smartpoint.controller.dto.RegistrationPJDto
import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.enum.ProfileEnum
import com.morfeu.smartpoint.util.PasswordUtil
import org.springframework.stereotype.Component

@Component
class LegalPersonRegistrationMapper {

    fun entityToDto(employee: Employee, company: Company): RegistrationPJDto =
        RegistrationPJDto(employee.name, employee.email, "", employee.cpf, company.cnpj, company.socialReason, employee.id)

    fun dtoToEmployeeEntity(registrationPJDto: RegistrationPJDto, company: Company): Employee =
        Employee(registrationPJDto.name, registrationPJDto.email,
            PasswordUtil().generatedBcrypt(registrationPJDto.password),
            registrationPJDto.cpf, ProfileEnum.ROLE_ADMIN, company.id.toString())

    fun dtoToCompanyEntity(registrationPJDto: RegistrationPJDto): Company = Company(registrationPJDto.reasonSocial, registrationPJDto.cnpj)
}