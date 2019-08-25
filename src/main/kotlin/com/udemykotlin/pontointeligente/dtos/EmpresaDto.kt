package com.udemykotlin.pontointeligente.dtos

/**
 * Classe que representa o DTO para empresa
 */
data class EmpresaDto (
        val razaoSocial: String,
        val cnpj: String,
        val id: String? = null
)