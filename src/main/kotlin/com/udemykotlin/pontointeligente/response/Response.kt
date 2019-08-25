package com.udemykotlin.pontointeligente.response

/**
 * Classe genérica de response.
 */
data class Response<T> (
        val erros: ArrayList<String> = arrayListOf(),
        var data: T? = null
)