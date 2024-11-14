package com.example.listofproductes.viewmodel



import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofproductes.model.MainRep
import com.example.listofproductes.model.ProductModel
import com.example.listofproductes.retrofit.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val mainRep: MainRep):ViewModel() {



      val weatherData = mutableStateListOf<ProductModel?>()


    private var _failure: MutableStateFlow<String> = MutableStateFlow("")
    val failure: StateFlow<String> get() = _failure

    private val _showShimmer = MutableLiveData<Boolean>()
    val showShimmer: LiveData<Boolean> get() = _showShimmer


    fun callproductsListApi() {
        viewModelScope.launch {
            _showShimmer.value= true
            when(val result = mainRep.callproductsListApi()){

                is NetworkResult.Success -> {
                    result.data?.let { products ->
                        weatherData.addAll(products)
                    }
                    _showShimmer.value = false
                }
                is NetworkResult.Error -> {
                    _failure.emit( result.message.toString())
                    _showShimmer.value= false

                }
            }
        }
    }


}