package com.morfeu.smartpoint.controller.mapper

import com.morfeu.smartpoint.controller.dto.EmployeeDto
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.util.PasswordUtil
import org.springframework.stereotype.Component

@Component
class EmployeeMapper {

    fun entityToDto(employeeUpdate: Employee): EmployeeDto =
        EmployeeDto(
            employeeUpdate.name,
            employeeUpdate.email,
            "",
            employeeUpdate.hourlyValue.toString(),
            employeeUpdate.hoursWorkDay.toString(),
            employeeUpdate.lunchHours.toString(),
            employeeUpdate.id
        )

    fun employeeUpdateData(employee: Employee, employeeDto: EmployeeDto): Employee {

        var password: String = if (employeeDto.password == null) { employee.password } else { PasswordUtil().generatedBcrypt(employeeDto.password) }

        return Employee(
            employeeDto.name,
            employeeDto.email,
            password,
            employee.cpf,
            employee.profile,
            employee.companyId,
            employeeDto.hourlyValue?.toDouble(),
            employeeDto.hoursWorkDay?.toFloat(),
            employeeDto.lunchHours?.toFloat(),
            employee.id)
    }
}