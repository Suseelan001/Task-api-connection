package com.example.listofproductes.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.listofproductes.model.ProductModel
import com.example.listofproductes.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun UserListScreen(navHostController: NavHostController, viewModel: ProductViewModel) {
    val showShimmer: Boolean by viewModel.showShimmer.observeAsState(
        initial = false
    )
    LaunchedEffect(Unit) {
        viewModel.callproductsListApi()
    }

    val groupedProducts = viewModel.weatherData
        .filterNot { it?.productCategory == null }
        .groupBy { it?.productCategory }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = {
                Text("Products")
            },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {

        if (showShimmer){
            items(10){
          ShimmerEffect()
}
        }else{
                         groupedProducts.forEach { (category, products) ->

                    item {
                        if (category != null) {
                            Text(
                                text = category,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                            )
                        }
                    }


                    itemsIndexed(products) { index, product ->
                        product?.let {
                            ProductItem(sneaker = it)
                            if (index < products.size - 1) {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }



                }

        }

    }
}}
@Composable
fun ProductItem(sneaker: ProductModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 13.dp, end = 13.dp)
            .background(Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFF5F5F5),
                                Color(0xFFE0E0E0)
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                sneaker.featuredImage?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it), // Load image from URL
                        contentDescription = "Product Image",
                        modifier = Modifier.size(200.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (sneaker.name?.contains(sneaker.brand ?: "", ignoreCase = true) == true) {
                        sneaker.name ?: ""
                    } else {
                        "${sneaker.brand ?: ""} ${sneaker.name ?: ""}"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )



                Spacer(modifier = Modifier.height(4.dp))

                sneaker.description?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Display Product Display Text (e.g., size or screen type)
                sneaker.display?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }


        }
    }
}


@Composable
fun ShimmerEffect() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color(0xFFc8c8c8), shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp)
                    .background(Color(0xFFFFFFFF))

            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(Color(0xFFFFFFFF))
                )


                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Color(0xFFFFFFFF))
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .background(Color(0xFFFFFFFF))
                )
            }


        }
    }
}

