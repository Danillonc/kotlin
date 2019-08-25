package com.udemykotlin.pontointeligente.services.impl

import com.udemykotlin.pontointeligente.documents.Funcionario
import com.udemykotlin.pontointeligente.repositories.FuncionarioRepository
import com.udemykotlin.pontointeligente.services.FuncionarioService
import org.apache.juli.logging.Log
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.logging.Logger

/**
 * Classe que representa a implementacao da camada de servico de Funcionario.
 */
@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    private val logger = LoggerFactory.getLogger(FuncionarioServiceImpl::class.java)

    override fun persistir(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario? {
        var funcionario: Funcionario? = null
        try {
            funcionario = funcionarioRepository.findByCpf(cpf)
        }catch (e: Exception){
            logger.info("Funcionario não encontrado pelo cpf informado.")
        }

        return funcionario
    }

    override fun buscarPorEmail(email: String): Funcionario? {
        var funcionario: Funcionario? = null
        try {
            funcionario = funcionarioRepository.findByEmail(email)
        }catch (e: Exception){
            logger.info("Funcionário não encontrado pelo email informado.")
        }
        return funcionario
    }

    override fun buscarPorId(id: String): Funcionario? = funcionarioRepository.findById(id).orElse(null)
}