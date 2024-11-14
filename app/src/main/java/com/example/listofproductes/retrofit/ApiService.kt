package com.example.listofproductes.retrofit

import com.example.listofproductes.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun callproductsListApi():  Response<List<ProductModel>>
}