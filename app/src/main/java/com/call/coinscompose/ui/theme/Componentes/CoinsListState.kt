package com.call.coinscompose.ui.theme.Componentes

import com.call.coinscompose.data.remote.dto.CoinsDto

data class CoinsListState (
    val isLoading: Boolean = false,
    val Coins: List<CoinsDto> = emptyList(),
    val error: String = ""
        )