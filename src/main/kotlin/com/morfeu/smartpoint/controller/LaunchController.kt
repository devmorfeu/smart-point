package com.morfeu.smartpoint.controller

import com.morfeu.smartpoint.controller.mapper.LaunchMapper
import com.morfeu.smartpoint.controller.dto.LaunchDto
import com.morfeu.smartpoint.entity.Launch
import com.morfeu.smartpoint.response.Response
import com.morfeu.smartpoint.service.IEmployeeService
import com.morfeu.smartpoint.service.ILaunchService
import com.morfeu.smartpoint.util.ValidatorUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.PageRequest.*
import org.springframework.data.domain.Sort.*
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/lancamentos")
class LaunchController(
    val iLaunchService: ILaunchService,
    val employeeService: IEmployeeService,
    val validatorUtil: ValidatorUtil,
    val launchMapper: LaunchMapper
) {

    @Value("\${pagination.quantity_per_page}")
    private val paginationPage: Int = 15

    @PostMapping
    fun add(@Valid @RequestBody launchDto: LaunchDto, result: BindingResult): ResponseEntity<Response<LaunchDto>> {

        val response: Response<LaunchDto> = Response()

        validatorUtil.employeeValid(launchDto, result)

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.errors.add(erro.defaultMessage!!)
            return badRequest().body(response)
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
            return badRequest().body(response)
        }

        response.data = launchMapper.entityToDto(launch)

        return status(OK).body(response)
    }

    @GetMapping("/funcionario/{funcionarioId}")
    fun listByEmployeeId(@PathVariable("funcionarioId") employeeId: String,
                         @RequestParam(value = "page", defaultValue = "0") page: Int,
                         @RequestParam(value = "order", defaultValue = "id") order: String,
                         @RequestParam(value = "direction", defaultValue = "DES") direction: String): ResponseEntity<Response<Page<LaunchDto>>>  {

        val response: Response<Page<LaunchDto>> = Response()

        val pageRequest: PageRequest = of(page, paginationPage, Direction.valueOf(direction), order)

        val launches: Page<Launch> = iLaunchService.findEmployeeById(employeeId, pageRequest)

        val launchesDto: Page<LaunchDto> = launches.map { launch -> launchMapper.entityToDto(launch) }

        response.data = launchesDto

        return  status(OK).body(response)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @Valid @RequestBody launchDto: LaunchDto, result: BindingResult): ResponseEntity<Response<LaunchDto>> {

        val response: Response<LaunchDto> = Response()

        validatorUtil.employeeValid(launchDto, result)
        launchDto.id = id
        val launch: Launch = launchMapper.dtoToEntity(launchDto,result)

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.errors.add(erro.defaultMessage!!)
            return badRequest().body(response)
        }

        iLaunchService.persist(launch)
        response.data = launchMapper.entityToDto(launch)
        return ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Response<String>> {

        val response: Response<String> = Response()
        val launch: Launch? = iLaunchService.findById(id)

        if (launch == null) {
            response.errors.add("Erro ao remover lançamento. Registro não encontrado para o id $id")
            return badRequest().body(response)
        }

        iLaunchService.remove(id)
        return ok(Response())
    }
}