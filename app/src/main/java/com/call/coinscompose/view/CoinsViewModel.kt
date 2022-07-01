package com.call.coinscompose.view


import androidx.lifecycle.viewModelScope
import com.call.coinscompose.data.remote.repository.CoinsRepository
import com.call.coinscompose.ui.theme.Componentes.CoinsListState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.coinscompose.data.remote.dto.CoinsDto
import com.call.coinscompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository
) : ViewModel(){

    var descripcion by mutableStateOf("")
    var valor by mutableStateOf("")

    private var _state = mutableStateOf(CoinsListState())
    val state: State<CoinsListState> = _state

    init {
        coinsRepository.getCoins().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinsListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinsListState(Coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinsListState(error = result.message?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun Guardar(){
        viewModelScope.launch {
            coinsRepository.postCoins(
                CoinsDto(
                    coinId = 0,
                    descripcion = descripcion,
                    valor = valor.toFloat(),
                    imagenUrl = ""
                )
            )
        }
    }
}