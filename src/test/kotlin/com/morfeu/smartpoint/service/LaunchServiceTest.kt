package com.morfeu.smartpoint.service

import com.morfeu.smartpoint.entity.Launch
import com.morfeu.smartpoint.enum.TypeEnum
import com.morfeu.smartpoint.repository.ILaunchRepository
import com.morfeu.smartpoint.service.impl.LaunchServiceImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

@SpringBootTest
class LaunchServiceTest {

    @Autowired
    private val launchService: ILaunchService? = null

    @MockBean
    private val iLaunchRepository: ILaunchRepository? = null

    private val id: String = "1"

    @BeforeEach
    fun setUp() {

        BDDMockito.given<Page<Launch>>(iLaunchRepository?.findByEmployeeId(id, PageRequest.of(0, 10)))
            .willReturn(PageImpl(ArrayList<Launch>()))
        BDDMockito.given(iLaunchRepository?.findById(id)).willReturn(Optional.of(launchData()))
        BDDMockito.given(iLaunchRepository?.save(any(Launch::class.java))).willReturn(launchData())
    }

    @Test
    fun `test find launch by employee id`() {
        val launch: Page<Launch>? = launchService?.findEmployeeById(id, PageRequest.of(0,10))
        assertNotNull(launch)
    }

    @Test
    fun `test find launch by id`() {
        val launch: Launch? = launchService?.findById(id)
        assertNotNull(launch)
    }

    @Test
    fun `test launch persist`() {
        val launch: Launch? = launchService?.persist(launchData())
        assertNotNull(launch)
    }

    private fun launchData() = Launch(Date(), TypeEnum.START_WORK, id)
}