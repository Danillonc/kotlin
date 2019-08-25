package com.udemykotlin.pontointeligente.services

import com.udemykotlin.pontointeligente.documents.Empresa

/**
 * Interface que representa que define os contratos de negócio para Empresas.
 */
interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa
}