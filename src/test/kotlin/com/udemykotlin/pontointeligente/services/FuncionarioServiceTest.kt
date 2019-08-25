package com.udemykotlin.pontointeligente.services

import com.udemykotlin.pontointeligente.documents.Funcionario
import com.udemykotlin.pontointeligente.enums.PerfilEnum
import com.udemykotlin.pontointeligente.repositories.FuncionarioRepository
import com.udemykotlin.pontointeligente.utils.SenhaUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/**
 * Classe que representa os testes para a camada de servico de funcionarios.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class FuncionarioServiceTest {

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    @Autowired
    private val funcionarioService: FuncionarioService? = null

    private val email: String = "email@email.com"
    private val cpf: String = "12345687901"
    private val id: String = "1"

    @Before
    @Throws(Exception::class)
    fun setUP(){
        BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java))).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findById(id)).willReturn(Optional.of(funcionario()))
        BDDMockito.given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())

    }

    @Test
    fun testPersistirFuncionario(){
        val funcionario: Funcionario? = this.funcionarioService?.persistir(funcionario())
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPrId(){
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorId(id)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmaail(){
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(email)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorCpf(){
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(cpf)
        Assert.assertNotNull(funcionario)
    }

    private fun funcionario(): Funcionario = Funcionario(
            "Nome", email,
             SenhaUtils().gerarBcrypt("123456"),
            cpf, PerfilEnum.ROLE_USUARIO, id)
}