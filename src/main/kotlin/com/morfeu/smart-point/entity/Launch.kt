package com.morfeu.pontointeligente.entity

import com.morfeu.pontointeligente.enum.TypeEnum
import org.springframework.data.annotation.Id
import java.util.*

class Launch (
    val data: Date,
    val type: TypeEnum,
    val employeeId: String,
    val description: String? = "",
    val localization: String? = "",
    @Id val id: String? = null
)