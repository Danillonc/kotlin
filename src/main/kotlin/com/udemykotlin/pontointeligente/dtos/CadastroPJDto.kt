package com.udemykotlin.pontointeligente.dtos

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import kotlin.math.min

/**
 * Classe que representa o DTO para cadastro de PJ.
 */
data class CadastroPJDto (


        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve ser entre 3 e 200 caracteres.")
        val nome: String = "",

        @get:NotEmpty(message = "Email não pode ser vazio.")
        @get:Length(min = 5, max = 200, message = "Email deve ser entre 5 e 200 caracteres.")
        @get:Email(message = "Email inválido.")
        val email: String = "",

        @get:NotEmpty(message = "Senha não pode ser inválida.")
        val senha: String = "",

        @get:NotEmpty(message = "CPF não pode ser vazio.")
        @get:CPF(message = "CPF inválido.")
        val cpf: String = "",

        @get:NotEmpty(message = "CNPJ não pode ser vazio.")
        @get:CNPJ(message = "CNPJ inválido.")
        val cnpj: String = "",

        @get:NotEmpty(message = "Razão social não pode ser vazio.")
        @get:Length(min = 5, max = 200, message = "Razão social deve ser entre 5 e 200 caracteres.")
        val razaoSocial: String = "",

        val id: String? = null
)