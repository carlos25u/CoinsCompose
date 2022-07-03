package com.call.coinscompose.ui.theme.Componentes

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.coinscompose.view.CoinsViewModel

@Composable
fun  registroCoins(backToListado:() -> Unit, viewModel: CoinsViewModel = hiltViewModel()){
    val  ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    var descripcionValidar by remember { mutableStateOf(false)}
    var valorValidar by remember { mutableStateOf(false)}
    val context = LocalContext.current


    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Registro de Coins") })
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    descripcionValidar = viewModel.descripcion.isBlank()
                    valorValidar = viewModel.valor.isBlank()

                    if(viewModel.descripcion.toString() == ""){
                        Toast.makeText(context, "Descripcion no debe estar vacio", Toast.LENGTH_SHORT).show()
                    }

                    if(viewModel.valor.toString() == ""){
                        Toast.makeText(context, "Valor no debe estar vacio", Toast.LENGTH_SHORT).show()
                    }

                    if(!descripcionValidar && !valorValidar){
                        if(viewModel.valor.toFloat() > 0){
                            viewModel.Guardar()
                            Toast.makeText(context, "Guardado", Toast.LENGTH_SHORT).show()
                            backToListado()
                        }else{
                            Toast.makeText(context, "El Valor debe de ser mayor a 0", Toast.LENGTH_SHORT).show()
                        }
                    }
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
                        imageVector = Icons.Default.Description,
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