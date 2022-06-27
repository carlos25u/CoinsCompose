package com.call.coinscompose.data.remote

import com.call.coinscompose.data.remote.dto.CoinsDto
import retrofit2.http.GET

interface CoinsApi {
    @GET("/Coins")
    suspend fun getCoins(): List<CoinsDto>
}