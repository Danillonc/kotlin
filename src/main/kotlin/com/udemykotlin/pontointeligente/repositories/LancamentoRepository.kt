package com.udemykotlin.pontointeligente.repositories

import com.udemykotlin.pontointeligente.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Camada que representa o repositorio para Lacamentos.
 */
interface LancamentoRepository: MongoRepository<Lancamento, String> {

    fun findByFuncionarioId(funcionarioId: String, pageable: Pageable): Page<Lancamento>
}