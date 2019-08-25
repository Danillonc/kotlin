package com.udemykotlin.pontointeligente.controller

import com.udemykotlin.pontointeligente.documents.Empresa
import com.udemykotlin.pontointeligente.documents.Funcionario
import com.udemykotlin.pontointeligente.dtos.CadastroPJDto
import com.udemykotlin.pontointeligente.enums.PerfilEnum
import com.udemykotlin.pontointeligente.response.Response
import com.udemykotlin.pontointeligente.services.EmpresaService
import com.udemykotlin.pontointeligente.services.FuncionarioService
import com.udemykotlin.pontointeligente.utils.SenhaUtils
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Classe que representa o controller para Pessoa Juridica.
 */
@RestController
@RequestMapping("/api/cadastrar-pj")
class CadastroPJController(val empresaService: EmpresaService,
                           val funcionarioService: FuncionarioService) {

    @PostMapping
    fun cadastrar(@Valid @RequestBody cadastroPJDto: CadastroPJDto,
                  result: BindingResult): ResponseEntity<Response<CadastroPJDto>> {

        val response: Response<CadastroPJDto> = Response<CadastroPJDto>()

        validarDadosExistentes(cadastroPJDto, result)
        if(result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage!!)
            return ResponseEntity.badRequest().body(response)
        }

        var empresa: Empresa = converterDtoParaEmpresa(cadastroPJDto)
        empresa = empresaService.persistir(empresa)

        var funcionario: Funcionario = converterDtoParaFuncionario(cadastroPJDto, empresa)
        funcionario = funcionarioService.persistir(funcionario)

        response.data = converterCadastroPjDto(funcionario, empresa)

        return ResponseEntity.ok(response)

    }

    /**
     * Função privada responsável por validar os dados enviados no request.
     */
    private fun validarDadosExistentes(cadastroPJDto: CadastroPJDto, result: BindingResult) {
        val empresa: Empresa? = empresaService?.buscarPorCnpj(cadastroPJDto.cnpj)
        if(empresa != null){
            result.addError(ObjectError("empresa", "Empresa já existente."))
        }

        val funcionarioCpf: Funcionario? = funcionarioService?.buscarPorCpf(cadastroPJDto.cpf)
        if(funcionarioCpf != null){
            result.addError(ObjectError("funcionario", "CPF já existente"))
        }

        val funcionarioEmail: Funcionario? = funcionarioService?.buscarPorEmail(cadastroPJDto.email)
        if(funcionarioEmail != null){
            result.addError(ObjectError("funcionario", "Email já existente"))
        }

    }

    /**
     * Função privada responsável por converter DTO para Empresa.
     */
    private fun converterDtoParaEmpresa(cadastroPJDto: CadastroPJDto): Empresa = Empresa(cadastroPJDto.razaoSocial, cadastroPJDto.cnpj)

    /**
     * Função privado responsável por converter DTO para Funcionario.
     */
    private fun converterDtoParaFuncionario(cadastroPJDto: CadastroPJDto, empresa: Empresa): Funcionario = Funcionario(cadastroPJDto.nome, cadastroPJDto.email,
            SenhaUtils().gerarBcrypt(cadastroPJDto.senha), cadastroPJDto.cpf, PerfilEnum.ROLE_ADMIN, empresa.id.toString())

    /**
     * Função privado responsável por converter os prâmetros para DTO.
     */
    private fun converterCadastroPjDto(funcionario: Funcionario, empresa: Empresa): CadastroPJDto = CadastroPJDto(funcionario.nome, funcionario.email,
            "", funcionario.cpf, empresa.cnpj, empresa.razaoSocial, funcionario.id)
}