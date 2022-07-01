package com.call.coinscompose.ui.theme.Componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.call.coinscompose.data.remote.dto.CoinsDto
import com.call.coinscompose.view.CoinsViewModel

@Composable
fun listadoCoins(goToRegistro :() -> Unit, viewModel: CoinsViewModel = hiltViewModel()){
    val  ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Coins") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    goToRegistro()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Nuevo")
            }
        },

        scaffoldState = ScaffoldState
    ){it

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.Coins){ Coins ->
                    coinsItem(
                        coins = Coins,
                        onClick = {})
                }
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Composable
fun coinsItem(coins: CoinsDto, onClick : (CoinsDto)-> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick(coins) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(8.dp),

                ) {
                AsyncImage(
                    model = "${coins.imagenUrl}",
                    contentDescription = null,

                    )

                Row(modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 5.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Text(
                        text = "${coins.descripcion}", )



                    Text(
                        text = "$ ${coins.valor}",)
                }
            }
        }
    }
}
