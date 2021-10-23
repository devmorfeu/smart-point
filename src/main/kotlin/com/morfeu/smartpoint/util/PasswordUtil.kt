package com.morfeu.smartpoint.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PasswordUtil {

    fun generatedBcrypt(password: String): String = BCryptPasswordEncoder().encode(password)
}