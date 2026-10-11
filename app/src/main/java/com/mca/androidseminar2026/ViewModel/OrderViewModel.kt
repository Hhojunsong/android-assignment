package com.mca.androidseminar2026.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {
    // 메뉴 id와 수량을 저장합니다. 수량이 0인 메뉴는 저장하지 않습니다.
    private val _quantities = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val quantities: StateFlow<Map<Int, Int>> = _quantities.asStateFlow()

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
}