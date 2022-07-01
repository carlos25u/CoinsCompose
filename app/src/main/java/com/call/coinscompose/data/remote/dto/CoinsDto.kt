package com.call.coinscompose.data.remote.dto

data class CoinsDto(
    val coinId: Int,
    val descripcion: String,
    val valor: String,
    val imagenUrl: String?
)