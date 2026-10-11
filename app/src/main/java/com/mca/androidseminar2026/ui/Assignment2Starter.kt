package com.mca.androidseminar2026.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mca.androidseminar2026.ViewModel.OrderViewModel
import com.mca.androidseminar2026.model.OrderTab

@Composable
fun Assignment2Starter(
    viewModel: OrderViewModel = viewModel()
) {
    var selectedTab by rememberSaveable {
        mutableStateOf(OrderTab.RESTAURANT)
    }
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    val quantities by viewModel.quantities.collectAsState()
    val totalQuantity = quantities.values.sum()

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedTab = selectedTab,
                totalQuantity = totalQuantity,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                OrderTab.RESTAURANT -> RestaurantScreen(
                    isFavorite = isFavorite,
                    onFavoriteClick = { isFavorite = !isFavorite }
                )
                OrderTab.MENU -> MenuScreen(viewModel = viewModel)
                OrderTab.CART -> CartScreen(viewModel = viewModel)
            }
        }
    }
}
