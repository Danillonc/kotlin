package com.udemykotlin.pontointeligente.repositories

import com.udemykotlin.pontointeligente.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Camada que representa o repositório para empresas.
 */
interface EmpresaRepository: MongoRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa

}