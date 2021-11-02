package com.morfeu.smartpoint.controller

import com.morfeu.smartpoint.controller.dto.EmployeeDto
import com.morfeu.smartpoint.controller.mapper.EmployeeMapper
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.response.Response
import com.morfeu.smartpoint.service.IEmployeeService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.status
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/funcionarios")
class EmployeeController(val iEmployeeService: IEmployeeService, val mapper: EmployeeMapper) {

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @Valid @RequestBody employeeDto: EmployeeDto, result: BindingResult): ResponseEntity<Response<EmployeeDto>> {

        val response: Response<EmployeeDto> = Response()
        val employee: Employee? = iEmployeeService.findById(id)

        if (employee == null) { result.addError(ObjectError("funcionario", "Funcionário não encontrado.")) }

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.errors.add(erro.defaultMessage!!)
            return badRequest().body(response)
        }

        val employeeUpdate: Employee = mapper.employeeUpdateData(employee!!, employeeDto)
        iEmployeeService.persist(employeeUpdate)

        response.data = mapper.entityToDto(employeeUpdate)
        return status(CREATED).body(response)
    }
}