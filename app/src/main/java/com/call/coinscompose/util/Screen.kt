package com.call.coinscompose.util

sealed class Screen(val route: String) {
    object coinsRegistroScreen: Screen("coinsRegistroScreen")
    object coinsListadoScreen: Screen("coinsListadoScreen")
}