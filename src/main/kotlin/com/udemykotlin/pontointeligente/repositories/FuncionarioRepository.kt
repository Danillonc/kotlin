package com.udemykotlin.pontointeligente.repositories

import com.udemykotlin.pontointeligente.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Camada que representa o repositório para funcionarios.
 */
interface FuncionarioRepository: MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario

    fun findByCpf(cpf: String): Funcionario



}