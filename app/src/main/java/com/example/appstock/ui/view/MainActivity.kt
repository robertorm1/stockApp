package com.example.appstock.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appstock.data.model.response
import com.example.appstock.data.model.stockModel
import com.example.appstock.ui.theme.AppStockTheme
import com.example.appstock.ui.viewModel.stockViewModel
import com.example.appstock.R.drawable.carrito
import kotlinx.coroutines.delay
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    private val stockViewModel:stockViewModel by viewModels()
    private var stock1:List<response> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stockViewModel.getStock()

        stockViewModel.modelStock.observe(this, Observer { stock ->
            stock1 = stock.response;
        })

        stockViewModel.isloading.observe(this, Observer { estado ->
        })

        setContent {
            AppStockTheme {
                val navControler = rememberNavController();
                Navigation(navControler,stock1,stockViewModel)

            }
        }
    }
}

@Composable
fun Navigation(navControler: NavHostController,stock:List<response>,viewModel: stockViewModel){

    NavHost(navController = navControler, startDestination = "ViewSplash" ){
        composable("ViewSplash"){
            ViewSplash(navController = navControler)
        }
        composable("ViewContent"){
            ViewContent(stock = viewModel.modelStock.value!!.response)
        }
    }

}

@Composable
fun ViewSplash(navController: NavController){

    LaunchedEffect(key1 = true){
        delay(5000L)
        navController.navigate("ViewContent")
    }
    Box(modifier = Modifier
        .background(Color.Blue)
        .fillMaxSize(),contentAlignment = Alignment.Center){
        Icon(
            modifier = Modifier
                .size(120.dp),
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}

@Composable
fun ViewContent (stock:List<response>){
    Scaffold(
        topBar = { Toolbar()},
        content = { ConversationList(stock =stock )}
    )
}

@Composable
fun Toolbar(){
    TopAppBar( title = { Text(text = "Inventario")})
}

@Composable
fun ConversationList(stock:List<response>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(stock) {
            ConversationItem(response = it)
        }
    }
}

@Composable
fun ConversationItem(response:response){
    Row(
        modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .size(80.dp).padding(8.dp),
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Logo Icon",
            tint = Color.Blue
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = response.nombre_producto
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Stock: "+response.id_stock
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppStockTheme {
      var stock8:List<response> = arrayListOf<response>(
           response(1,2,"pomada"),
           response(1,2,"pomada")
       )
        ConversationList(stock = stock8)
    }
}