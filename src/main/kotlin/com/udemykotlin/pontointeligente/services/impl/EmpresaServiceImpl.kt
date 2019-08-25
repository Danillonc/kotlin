package com.udemykotlin.pontointeligente.services.impl

import com.udemykotlin.pontointeligente.documents.Empresa
import com.udemykotlin.pontointeligente.repositories.EmpresaRepository
import com.udemykotlin.pontointeligente.services.EmpresaService
import org.springframework.stereotype.Service
import java.lang.Exception

/**
 * Classe que representa a implementação de regras de negócio para Empresas.
 */
@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {


    override fun buscarPorCnpj(cnpj: String): Empresa? {
        try {
            return empresaRepository.findByCnpj(cnpj)
        }catch (e: Exception){
            return null
        }
    }

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)


}