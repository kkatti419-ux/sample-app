package com.kartik.myapplication.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartik.myapplication.domain.model.ProductList
import com.kartik.myapplication.domain.repository.ProductRepository
import com.kartik.myapplication.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository   // ✅ interface
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Idle)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _startDestination = MutableStateFlow(Screen.Home.route)
    val startDestination: StateFlow<String> = _startDestination

    fun getAllProducts() {
        _uiState.value = ProductUiState.Loading

        viewModelScope.launch {
            val result = productRepository.getProducts()  // ✅ proper call

            result
                .onSuccess {
                    _uiState.value = ProductUiState.Success(it)
                }
                .onFailure {
                    _uiState.value =
                        ProductUiState.Error(it.message ?: "Something went wrong")
                }
        }
    }
}


sealed class ProductUiState {
    object Idle : ProductUiState()
    object Loading : ProductUiState()
    data class Success(val data: ProductList) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}