package com.morfeu.smartpoint.controller.mapper

import com.morfeu.smartpoint.controller.dto.RegistrationPFDto
import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.enum.ProfileEnum
import com.morfeu.smartpoint.util.PasswordUtil
import org.springframework.stereotype.Component

@Component
class PhysicalPersonRegistrationMapper {

    fun entityToDto(employee: Employee, company: Company): RegistrationPFDto =
        RegistrationPFDto(
            employee.name,
            employee.email, "",
            employee.cpf,
            company.cnpj,
            company.id.toString(),
            employee.hourlyValue.toString(),
            employee.hoursWorkDay.toString(),
            employee.lunchHours.toString(),
            employee.id
        )

    fun dtoToEmployeeEntity(registrationPFDto: RegistrationPFDto, company: Company): Employee =
        Employee(
            registrationPFDto.name,
            registrationPFDto.email,
            PasswordUtil().generatedBcrypt(registrationPFDto.password),
            registrationPFDto.cpf,
            ProfileEnum.ROLE_USER,
            company.id.toString(),
            registrationPFDto.hourlyValue?.toDouble(),
            registrationPFDto.hoursWorkDay?.toFloat(),
            registrationPFDto.lunchHours?.toFloat(),
            registrationPFDto.id
        )
}