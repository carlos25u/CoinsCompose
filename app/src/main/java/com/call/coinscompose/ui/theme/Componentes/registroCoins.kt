package com.call.coinscompose.ui.theme.Componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.coinscompose.view.CoinsViewModel

@Composable
fun  registroCoins(backToListado:() -> Unit, viewModel: CoinsViewModel = hiltViewModel()){
    val  ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Registro de Coins") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.Guardar()
                    backToListado()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Nuevo")
            }
        },

        scaffoldState = ScaffoldState
    ) { it

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {

            OutlinedTextField(
                value = viewModel.descripcion,
                onValueChange = {viewModel.descripcion = it},
                label = { Text(text = "Descripcion")},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.MonetizationOn,
                        contentDescription = null)}
            )

            OutlinedTextField(
                value = viewModel.valor,
                onValueChange = {viewModel.valor = it},
                label = { Text(text = "Valor")},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null)}
            )
        }

    }
}