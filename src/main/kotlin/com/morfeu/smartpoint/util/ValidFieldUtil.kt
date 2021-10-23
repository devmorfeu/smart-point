package com.morfeu.smartpoint.util

import com.morfeu.smartpoint.dto.LaunchDto
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.service.IEmployeeService
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

@Component
class ValidFieldUtil (val iEmployeeService: IEmployeeService) {

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
}