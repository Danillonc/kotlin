package com.udemykotlin.pontointeligente.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Classe utilitaria para geracao de senha.
 */
class SenhaUtils {

    fun gerarBcrypt(senha: String): String = BCryptPasswordEncoder().encode(senha)
}