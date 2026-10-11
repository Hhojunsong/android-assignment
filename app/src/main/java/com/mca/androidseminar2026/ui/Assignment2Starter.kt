package com.mca.androidseminar2026.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mca.androidseminar2026.model.OrderTab
@Composable
fun Assignment2Starter() {
    // 버튼 → ViewModel → UiState → UI 연결을 보여 주는 실행 예제입니다.
    // TODO: 예제를 참고해 직접 설계한 과제 화면·상태·이벤트 연결로 교체하세요.
    var selectedTab by rememberSaveable {
        mutableStateOf(OrderTab.RESTAURANT)
    }
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedTab = selectedTab,
                totalQuantity = 0,
                onTabSelected = { selectedTab = it }
            )
        }
    ) {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                OrderTab.RESTAURANT -> RestaurantScreen(
                isFavorite = isFavorite,
                onFavoriteClick = { isFavorite = !isFavorite }
            )
                OrderTab.MENU -> MenuScreen()
                OrderTab.CART -> CartScreen()
            }
        }
    }
}
