package com.kartik.myapplication.presentation.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartik.myapplication.domain.model.ProductDetail
import com.kartik.myapplication.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val productId: Long = savedStateHandle.get<Long>("product_id") ?: -1L

    private val _state = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val state: StateFlow<ProductDetailUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.value = ProductDetailUiState.Loading
            if (productId < 0) {
                _state.value = ProductDetailUiState.Error("Invalid product")
                return@launch
            }
            productRepository.getProductById(productId)
                .onSuccess { _state.value = ProductDetailUiState.Success(it) }
                .onFailure { _state.value = ProductDetailUiState.Error(it.message ?: "Failed to load product") }
        }
    }
}

sealed class ProductDetailUiState {
    object Loading : ProductDetailUiState()
    data class Success(val product: ProductDetail) : ProductDetailUiState()
    data class Error(val message: String) : ProductDetailUiState()
}
