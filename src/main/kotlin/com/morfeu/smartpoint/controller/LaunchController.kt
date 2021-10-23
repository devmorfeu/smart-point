package com.morfeu.smartpoint.controller

import com.morfeu.smartpoint.controller.mapper.LaunchMapper
import com.morfeu.smartpoint.dto.LaunchDto
import com.morfeu.smartpoint.entity.Launch
import com.morfeu.smartpoint.response.Response
import com.morfeu.smartpoint.service.IEmployeeService
import com.morfeu.smartpoint.service.ILaunchService
import com.morfeu.smartpoint.util.ValidFieldUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/lancamentos")
class LaunchController(
    val iLaunchService: ILaunchService,
    val employeeService: IEmployeeService,
    val validFieldUtil: ValidFieldUtil,
    val launchMapper: LaunchMapper
) {

    @Value("\${pagination.quantity_per_page}")
    private val paginationPage: Int = 15

    @PostMapping
    fun add(@Valid @RequestBody launchDto: LaunchDto, result: BindingResult): ResponseEntity<Response<LaunchDto>> {

        val response: Response<LaunchDto> = Response()

        validFieldUtil.employeeValid(launchDto, result)

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.errors.add(erro.defaultMessage!!)
            return ResponseEntity.badRequest().body(response)
        }

        val launch: Launch = launchMapper.dtoToEntity(launchDto, result)

        iLaunchService.persist(launch)

        response.data = launchMapper.entityToDto(launch)

        return status(CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id: String): ResponseEntity<Response<LaunchDto>> {

        val response: Response<LaunchDto> = Response()

        val launch: Launch? = iLaunchService.findById(id)

        if (launch == null) {
            response.errors.add("LANÇAMENTO NÃO ENCONTRADO PARA O ID $id")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = launchMapper.entityToDto(launch)

        return status(OK).body(response)
    }
}