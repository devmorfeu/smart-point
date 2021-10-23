package com.morfeu.smartpoint.controller.mapper

import com.morfeu.smartpoint.dto.LaunchDto
import com.morfeu.smartpoint.entity.Launch
import com.morfeu.smartpoint.enum.TypeEnum
import com.morfeu.smartpoint.service.ILaunchService
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import java.text.SimpleDateFormat

@Component
class LaunchMapper(val iLaunchService: ILaunchService) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

     fun dtoToEntity(launchDto: LaunchDto, result: BindingResult): Launch {

        if (launchDto.id != null) {

            val launch: Launch? = iLaunchService.findById(launchDto.id!!)

            if (launch == null) result.addError(ObjectError("launch", "LANÇAMENTO NÃO ENCONTRADO."))
        }

        return Launch(dateFormat.parse(launchDto.date), TypeEnum.valueOf(launchDto.type!!),
            launchDto.employeeId!!, launchDto.description, launchDto.localization, launchDto.id)
    }

     fun entityToDto(launch: Launch): LaunchDto =
        LaunchDto(dateFormat.format(launch.data), launch.type.toString(), launch.description, launch.localization, launch.employeeId, launch.id)
}