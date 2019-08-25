package com.udemykotlin.pontointeligente.controllers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.udemykotlin.pontointeligente.documents.Funcionario
import com.udemykotlin.pontointeligente.documents.Lancamento
import com.udemykotlin.pontointeligente.dtos.LancamentoDto
import com.udemykotlin.pontointeligente.enums.PerfilEnum
import com.udemykotlin.pontointeligente.enums.TipoEnum
import com.udemykotlin.pontointeligente.services.FuncionarioService
import com.udemykotlin.pontointeligente.services.LancamentoService
import com.udemykotlin.pontointeligente.utils.SenhaUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Matchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat
import java.util.*

/**
 * classe que representa o teste para o controller de lancamentos.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class LancamentoControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val lancamentoService: LancamentoService? = null

    @MockBean
    private val funcionarioService: FuncionarioService? = null

    private val urlBase: String = "/api/lancamentos/"
    private val idFuncionario: String = "1"
    private val idLancamento: String = "1"
    private val tipo: String = TipoEnum.INICIO_TRABALHO.name
    private val data: Date = Date()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

   /* @Test
    @Throws(Exception::class)
    @WithMockUser
    fun testCadastrarFuncionario() {

        val lancamento: Lancamento = obterDadosLancamento()

        BDDMockito.given<Funcionario>(funcionarioService?.buscarPorId(idFuncionario)).willReturn(funcionario())
        BDDMockito.given(lancamentoService?.persistir(obterDadosLancamento())).willReturn(lancamento)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(obterJsonRequisicaoPost())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.tipo").value(tipo))
                .andExpect(jsonPath("$.data.data").value(dateFormat.format(data)))
                .andExpect(jsonPath("$.data.funcionarioId").value(idFuncionario))
                .andExpect(jsonPath("$.erros").isEmpty)

    }

    @Test
    @Throws(Exception::class)
    @WithMockUser
    fun testCadastrarLancamentoFuncionarioInvalido() {
        BDDMockito.given<Funcionario>(funcionarioService?.buscarPorId(idFuncionario)).willReturn(null)

        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
                .content(obterJsonRequisicaoPost())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(jsonPath("$.errors").value("Funcionario não encontrado. ID inexistente."))
                .andExpect(jsonPath("$.data").isEmpty)
    } */

    @Test
    @Throws(Exception::class)
    @WithMockUser(username = "admin@admin.com", roles = arrayOf("ADMIN"))
    fun testRemoverLancamento() {
        BDDMockito.given<Lancamento>(lancamentoService?.buscarPorId(idLancamento)).willReturn(obterDadosLancamento())

        mvc!!.perform(MockMvcRequestBuilders.delete(urlBase + idLancamento)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

    private fun funcionario(): Funcionario = Funcionario("Nome", "email@email.com", SenhaUtils().gerarBcrypt("123456"), "13920450708", PerfilEnum.ROLE_USUARIO, idFuncionario)

    private fun obterDadosLancamento(): Lancamento = Lancamento(data, TipoEnum.valueOf(tipo), idFuncionario, "Descrição", "1.234.4.345", idLancamento)

    @Throws(JsonProcessingException::class)
    private fun obterJsonRequisicaoPost(): String {
        val lancamentoDto: LancamentoDto = LancamentoDto(dateFormat.format(data), tipo, "Descrição", "1.234.4.234", idFuncionario)
        val mapper = ObjectMapper()
        return  mapper.writeValueAsString(lancamentoDto)
    }


}