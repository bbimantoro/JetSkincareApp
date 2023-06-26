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
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel =
        viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllSkincare()
            }

            is UiState.Success -> {
                HomeContent(
                    orderSkincare = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderSkincare: List<OrderSkincare>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        contentPadding = PaddingValues(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
    ) {
        items(orderSkincare) { data ->
            SkincareItem(
                thumbnail = data.skincare.thumbnail,
                name = data.skincare.name,
                price = data.skincare.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.skincare.id)
                }
            )
        }
    }
}