package com.morfeu.smartpoint.controller

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.morfeu.smartpoint.dto.LaunchDto
import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.entity.Launch
import com.morfeu.smartpoint.enum.ProfileEnum
import com.morfeu.smartpoint.enum.TypeEnum
import com.morfeu.smartpoint.enum.TypeEnum.START_WORK
import com.morfeu.smartpoint.service.IEmployeeService
import com.morfeu.smartpoint.service.ILaunchService
import com.morfeu.smartpoint.util.PasswordUtil
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class LaunchControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val iLaunchService: ILaunchService? = null

    @MockBean
    private val iEmployeeService: IEmployeeService? = null

    private val urlBase: String = "/api/lancamentos/"
    private val employeeId: String = "1"
    private val launchId: String = "1"
    private val type: String = START_WORK.name
    private val date: Date = Date()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Test
    @Throws(Exception::class)
    @WithMockUser(username = "email@email", authorities = ["ADMIN","USER"])
    fun `test register launch`() {

        val launch: Launch = launch()

        BDDMockito.given<Employee>(iEmployeeService?.findById(employeeId)).willReturn(employee())
        BDDMockito.given(iLaunchService?.persist(launch())).willReturn(launch)

        mvc!!.perform(post(urlBase).with(user("email@email").password("password").roles("USER")).content(getJsonData()).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.tipo").value(type))
            .andExpect(jsonPath("$.data.data").value(dateFormat.format(date)))
            .andExpect(jsonPath("$.data.funcionarioId").value(employeeId))
            .andExpect(jsonPath("$.erros").isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun `test register invalid functional entry`() {

        BDDMockito.given<Employee>(iEmployeeService?.findById(employeeId)).willReturn(null)

        mvc!!.perform(post(urlBase).content(getJsonData()).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors").value("Funcionário não encontrado. ID inexistente"))
            .andExpect(jsonPath("$.data").isEmpty())
    }

    @Test
    fun `test remove launch`() {

        BDDMockito.given<Launch>(iLaunchService?.findById(launchId)).willReturn(launch())

        mvc!!.perform(delete(urlBase + launchId).accept(APPLICATION_JSON)).andExpect(status().isOk())
    }

    @Throws(JsonProcessingException::class)
    private fun getJsonData(): String {
        val launchDto = LaunchDto(dateFormat.format(date), type, "descrição", "1.234,5.345", employeeId)
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(launchDto)
    }

    private fun launch(): Launch =
        Launch(date, TypeEnum.valueOf(type), employeeId, "descrição", "1.234,5.345", launchId)

    private fun employee(): Employee =
        Employee("JOAO", "email@email", PasswordUtil().generatedBcrypt("123456"), "46632692988", ProfileEnum.ROLE_USER, employeeId)
}