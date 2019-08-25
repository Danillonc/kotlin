package com.udemykotlin.pontointeligente.services

import com.udemykotlin.pontointeligente.documents.Funcionario

/**
 * Interfaace que define contratos para camada de servico dos funcionarios.
 */
interface FuncionarioService {

    fun persistir(funcionario: Funcionario): Funcionario

    fun buscarPorCpf(cpf: String): Funcionario?

    fun buscarPorEmail(email: String): Funcionario?

    fun buscarPorId(id: String): Funcionario?
}