package com.call.coinscompose.data.remote.repository

import com.call.coinscompose.data.remote.CoinsApi
import com.call.coinscompose.data.remote.dto.CoinsDto
import com.call.coinscompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val api : CoinsApi
){
    fun getCoins(): Flow<Resource<List<CoinsDto>>> = flow {
        try {
            emit(Resource.Loading())
            val exchanges = api.getCoins()
            emit(Resource.Success(exchanges))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}