package com.morfeu.smartpoint.service

import com.morfeu.smartpoint.entity.Employee
import com.morfeu.smartpoint.enum.ProfileEnum
import com.morfeu.smartpoint.repository.IEmployeeRepository
import com.morfeu.smartpoint.util.PasswordUtil
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.event.annotation.BeforeTestClass
import java.util.*

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    @Autowired
    private val iEmployeeService: IEmployeeService? = null

    @MockBean
    private val iEmployeeRepository: IEmployeeRepository? = null

    private val email: String = "email@emai.com"
    private val cpf: String = "46632684844"
    private val id: String = "1"
    
    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(iEmployeeRepository?.save(any(Employee::class.java))).willReturn(employeeData())
        BDDMockito.given(iEmployeeRepository?.findByCpf(cpf)).willReturn(employeeData())
        BDDMockito.given(iEmployeeRepository?.findByEmail(email)).willReturn(employeeData())
        BDDMockito.given(iEmployeeRepository?.findById(id)).willReturn(Optional.of(employeeData()))
    }

    @Test
    fun `test employee persist`() {
        val employee: Employee? = this.iEmployeeService?.persist(employeeData())
        assertNotNull(employee)
    }

    @Test
    fun `test find employee by id`() {
        val employee: Employee? = this.iEmployeeService?.findById(id)
        assertNotNull(employee)
    }

    @Test
    fun `test find employee by email`() {
        val employee: Employee? = this.iEmployeeService?.findByEmail(email)
        assertNotNull(employee)
    }

    @Test
    fun `test find employee by cpf`() {
        val employee: Employee? = this.iEmployeeService?.findByCpf(cpf)
        assertNotNull(employee)
    }

    private fun employeeData(): Employee =
        Employee("MC POZE", email, PasswordUtil().generatedBcrypt("123456"), cpf, ProfileEnum.ROLE_USER, id)
}