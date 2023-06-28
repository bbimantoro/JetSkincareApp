package com.academy.bangkit.jetskincare.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.di.Injection
import com.academy.bangkit.jetskincare.ui.ViewModelFactory
import com.academy.bangkit.jetskincare.ui.common.UiState
import com.academy.bangkit.jetskincare.ui.components.CartItem
import com.academy.bangkit.jetskincare.ui.components.OrderButton
import com.academy.bangkit.jetskincare.ui.components.TopAppBar

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderSkincare()
            }

            is UiState.Success -> {
                CartContent(
                    state = uiState.data,
                    onProductCountChanged = { skincareId, count ->
                        viewModel.updateOrderSkincare(skincareId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun CartContent(
    modifier: Modifier = Modifier,
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
) {
    val shareMessage = stringResource(
        id = R.string.share_message,
        state.orderSkincare.joinToString { it.skincare.name },
        state.totalPrice
    )

    Column(modifier = modifier.fillMaxSize()) {

        TopAppBar(title = stringResource(id = R.string.menu_cart))

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f),
        ) {
            items(state.orderSkincare, key = { it.skincare.id }) { item ->
                CartItem(
                    skincareId = item.skincare.id,
                    thumbnail = item.skincare.thumbnail,
                    name = item.skincare.name,
                    price = item.skincare.price * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChanged,
                )
                Divider()
            }
        }

        OrderButton(
            text = stringResource(id = R.string.total_price, state.totalPrice),
            enabled = state.orderSkincare.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)

            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
