package com.example.listofproductes.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.listofproductes.model.ProductModel
import com.example.listofproductes.viewmodel.ProductViewModel
import com.example.productiveness.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String, viewModel: ProductViewModel, navController: NavHostController) {
    var product: ProductModel? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        productId.toIntOrNull()?.let { id ->
            product = viewModel.weatherData.find { it?.id == id }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 10.dp)
    ) {
        // Top Bar with Back Icon and Model Name
        TopAppBar(
            title = {
                Text(text = product?.name ?: "Product Detail", fontWeight = FontWeight.Bold)
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack() // Navigate back when the back icon is clicked
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Details
        product?.let {
                AsyncImage(
                    model = it.featuredImage,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray),
                    error = painterResource(id = R.drawable.baseline_error_24),

                    )


            Spacer(modifier = Modifier.height(16.dp))

            // Product Name and Brand
            Text(
                text = if (it.name?.contains(
                        it.brand ?: "",
                        ignoreCase = true
                    ) == true
                ) {
                    it.name ?: ""
                } else {
                    "${it.brand ?: ""} ${it.name ?: ""}"
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Product Category and Display
            Text(
                text = it.productCategory ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = it.description ?: "",
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Price and Stock Status
            Text(
                text = "Price: $${it.basePrice}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (it.inStock == true) "In Stock (${it.stock} available)" else "Out of Stock",
                fontSize = 14.sp,
                color = if (it.inStock == true) Color.Green else Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Storage Options
            Text(
                text = "Storage Options:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            it.storageOptions?.forEach { storage ->
                Text(
                    text = storage,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Color Options
            Text(
                text = "Color Options:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            it.colorOptions?.forEach { color ->
                Text(
                    text = color,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display
            Text(
                text = "Display: ${it.display}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CPU
            Text(
                text = "CPU: ${it.CPU}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Camera Details
            Text(
                text = "Camera:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Rear: ${it.camera?.rearCamera ?: "N/A"}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                text = "Front: ${it.camera?.frontCamera ?: "N/A"}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        } ?: run {
            // Display a loading or placeholder UI while the product data is being loaded
            Text("Loading product details...", fontSize = 18.sp, color = Color.Gray)
        }
    }
}

