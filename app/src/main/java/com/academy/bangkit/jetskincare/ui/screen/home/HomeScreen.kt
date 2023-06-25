package com.academy.bangkit.jetskincare.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.di.Injection
import com.academy.bangkit.jetskincare.model.OrderSkincare
import com.academy.bangkit.jetskincare.ui.ViewModelFactory
import com.academy.bangkit.jetskincare.ui.common.UiState
import com.academy.bangkit.jetskincare.ui.components.SkincareItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel =
        viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllSkincare()
            }

            is UiState.Success -> {
                HomeContent(orderSkincare = uiState.data, modifier = modifier)
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderSkincare: List<OrderSkincare>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        items(orderSkincare) { data ->
            SkincareItem(
                image = data.skincare.image,
                name = data.skincare.name,
                price = data.skincare.price,
                modifier = Modifier.clickable {

                }
            )
        }
    }
}