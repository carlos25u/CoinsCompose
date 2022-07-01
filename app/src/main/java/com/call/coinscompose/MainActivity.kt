package com.call.coinscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.call.coinscompose.data.remote.dto.CoinsDto
import com.call.coinscompose.ui.theme.CoinsComposeTheme
import com.call.coinscompose.ui.theme.Componentes.listadoCoins
import com.call.coinscompose.ui.theme.Componentes.registroCoins
import com.call.coinscompose.util.Screen
import com.call.coinscompose.view.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           myApp()
        }
    }
}

@Composable
fun myApp() {
    CoinsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navHostController = rememberNavController()
            NavHost(navController = navHostController,
                startDestination = Screen.coinsListadoScreen.route){

                composable(route  = Screen.coinsListadoScreen.route){
                    listadoCoins(goToRegistro = {navHostController.navigate(Screen.coinsRegistroScreen.route)})
                }
                
                composable(route = Screen.coinsRegistroScreen.route){
                    registroCoins(backToListado = {navHostController.navigate(Screen.coinsListadoScreen.route)})
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinsComposeTheme {
        myApp()
    }
}