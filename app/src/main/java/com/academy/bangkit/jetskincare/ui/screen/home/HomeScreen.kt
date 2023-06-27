package com.academy.bangkit.jetskincare.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.di.Injection
import com.academy.bangkit.jetskincare.model.OrderSkincare
import com.academy.bangkit.jetskincare.ui.ViewModelFactory
import com.academy.bangkit.jetskincare.ui.common.UiState
import com.academy.bangkit.jetskincare.ui.components.Search
import com.academy.bangkit.jetskincare.ui.components.SkincareItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel =
        viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllSkincare()
            }

            is UiState.Success -> {
                Column {
                    Search(query = query, onQueryChange = viewModel::search)

                    if (uiState.data.isEmpty()) {
                        NotFound()
                    } else {
                        HomeContent(
                            orderSkincare = uiState.data,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail,
                        )
                    }
                }
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

@Composable
fun NotFound(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.not_found),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
            )
        )
    }
}