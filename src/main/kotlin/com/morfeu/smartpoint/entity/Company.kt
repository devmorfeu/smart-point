package com.morfeu.smartpoint.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Company (val socialReason: String , val cnpj: String, @Id val id: String? = null)