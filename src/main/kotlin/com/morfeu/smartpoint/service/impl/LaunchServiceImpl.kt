package com.morfeu.smartpoint.service.impl

import com.morfeu.smartpoint.entity.Launch
import com.morfeu.smartpoint.repository.ILaunchRepository
import com.morfeu.smartpoint.service.ILaunchService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LaunchServiceImpl(val iLaunchRepository: ILaunchRepository) : ILaunchService {

    override fun findEmployeeById(employeeId: String, pageRequest: PageRequest): Page<Launch> =
        iLaunchRepository.findByEmployeeId(employeeId, pageRequest)

    override fun findById(id: String): Launch? = iLaunchRepository.findById(id).orElseThrow()

    override fun persist(launch: Launch): Launch = iLaunchRepository.save(launch)

    override fun remove(id: String) = iLaunchRepository.deleteById(id)
}