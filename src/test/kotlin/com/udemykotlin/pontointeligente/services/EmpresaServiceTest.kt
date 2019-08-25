package com.udemykotlin.pontointeligente.services

import com.udemykotlin.pontointeligente.documents.Empresa
import com.udemykotlin.pontointeligente.repositories.EmpresaRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.lang.Exception

/**
 * Classe que representa os testes para a camada de serviço de empresas.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class EmpresaServiceTest {

    @Autowired
    val empresaService: EmpresaService? = null

    @MockBean
    private val empresaRepository: EmpresaRepository? = null

    private val CNPJ = "12345678901478"

    @Before
    @Throws(Exception::class)
    fun setUP() {
        BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(empresa())
        BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
    }

    @Test
    fun testBuscarEmpresaPorCnpj() {
        val empresa: Empresa? = empresaService?.buscarPorCnpj(CNPJ)
        Assert.assertNotNull(empresa)
    }

    @Test
    fun testPersistirEmpresa() {
        val empresa: Empresa? = empresaService?.persistir(empresa())
        Assert.assertNotNull(empresa)
    }

    private fun empresa(): Empresa = Empresa("Teste Razao Social Kotlin", CNPJ, "1")


}