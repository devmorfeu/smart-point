package com.morfeu.smartpoint.util

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PasswordUtilTest {

    private val PASSWORD = "1234"
    private val bCryptEncode = BCryptPasswordEncoder()

    @Test
    fun testGeneratedPasswordHah() {
        val hash = PasswordUtil().generatedBcrypt(PASSWORD)
        assertTrue(bCryptEncode.matches(PASSWORD, hash))
    }
}