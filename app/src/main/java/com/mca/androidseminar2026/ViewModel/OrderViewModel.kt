package com.mca.androidseminar2026.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.mca.androidseminar2026.data.MenuCategory


class OrderViewModel : ViewModel() {
    // 메뉴 id와 수량을 저장합니다. 수량이 0인 메뉴는 저장하지 않습니다.
    private val _quantities = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val quantities: StateFlow<Map<Int, Int>> = _quantities.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun increaseQuantity(menuId: Int) {
        _quantities.update { current ->
            current + (menuId to ((current[menuId] ?: 0) + 1))
        }
    }

    fun decreaseQuantity(menuId: Int) {
        _quantities.update { current ->
            val quantity = current[menuId] ?: 0

            if (quantity <= 1) {
                current - menuId
            } else {
                current + (menuId to quantity - 1)
            }
        }
    }

    fun clearCart() {
        _quantities.value = emptyMap()
    }

    fun refreshMenu() {
        if (_isRefreshing.value) return

        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                delay(500)
                // 실제 데이터는 변경하지 않음
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    private val _selectedCategory = MutableStateFlow<MenuCategory?>(null)
    val selectedCategory: StateFlow<MenuCategory?> =
        _selectedCategory.asStateFlow()

    fun selectCategory(category: MenuCategory?) {
        _selectedCategory.value = category
    }
}