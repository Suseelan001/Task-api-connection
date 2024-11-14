package com.example.listofproductes.model

import android.content.Context
import android.content.Context.MODE_PRIVATE

import com.example.listofproductes.retrofit.ApiService
import com.example.listofproductes.retrofit.BaseApiResponse
import com.example.listofproductes.retrofit.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRep@Inject constructor(private val context: Context,private val apiService: ApiService):
    BaseApiResponse() {



    suspend fun callproductsListApi(): NetworkResult<List<ProductModel>> {
      return  withContext(Dispatchers.IO){
            safeApiCall {
                apiService.callproductsListApi()
            }
        }
        }


}

