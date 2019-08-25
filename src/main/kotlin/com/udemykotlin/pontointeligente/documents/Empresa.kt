package com.udemykotlin.pontointeligente.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/*
*
* classe responsável por representar o document referente a empresa.
* */
@Document
data class Empresa (
        val razaoSocial: String,
        val cnpj: String,
        @Id val id: String? = null
)