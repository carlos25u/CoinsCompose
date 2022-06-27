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
import coil.compose.AsyncImage
import com.call.coinscompose.data.remote.dto.CoinsDto
import com.call.coinscompose.ui.theme.CoinsComposeTheme
import com.call.coinscompose.view.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinsComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    myApp()
                }
            }
        }
    }
}

@Composable
fun myApp(viewModel: CoinsViewModel = hiltViewModel()) {
    val  ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Coins") })
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinsComposeTheme {
        myApp()
    }
}