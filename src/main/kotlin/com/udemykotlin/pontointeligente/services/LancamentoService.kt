package com.udemykotlin.pontointeligente.services

import com.udemykotlin.pontointeligente.documents.Lancamento
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

/**
 * Interface responsável por definir os contratos da camada de Lancamentos.
 */
interface LancamentoService {

    fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento>

    fun buscarPorId(id: String): Lancamento?

    fun persistir(lancamento: Lancamento): Lancamento

    fun remover(id: String)


}