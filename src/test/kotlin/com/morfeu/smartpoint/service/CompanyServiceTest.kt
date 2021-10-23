package com.morfeu.smartpoint.service

import com.morfeu.smartpoint.entity.Company
import com.morfeu.smartpoint.repository.ICompanyRepository
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
import java.util.*
import kotlin.jvm.Throws

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CompanyServiceTest {

    @Autowired
    val iCompanyService: ICompanyService? = null

    @MockBean
    private val companyRepository: ICompanyRepository? = null

    private val CNPJ = "89441649000151"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(companyRepository?.findByCnpj(CNPJ)).willReturn(createCompany())
        BDDMockito.given(companyRepository?.save(any(Company::class.java))).willReturn(createCompany())
    }

    @Test
    fun `test find company by cnpj`() {
        val company: Company? = iCompanyService?.findByCnpj(CNPJ)
        assertNotNull(company)
    }

    @Test
    fun `test persist company`() {
        val company: Company? = iCompanyService?.persist(createCompany())
        assertNotNull(company)
    }

    private fun createCompany(): Company = Company("ANITA", CNPJ, UUID.randomUUID().toString())
}