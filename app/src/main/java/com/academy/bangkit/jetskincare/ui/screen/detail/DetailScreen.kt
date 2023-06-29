package com.academy.bangkit.jetskincare.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.di.Injection
import com.academy.bangkit.jetskincare.ui.ViewModelFactory
import com.academy.bangkit.jetskincare.ui.common.UiState
import com.academy.bangkit.jetskincare.ui.components.OrderButton
import com.academy.bangkit.jetskincare.ui.components.ProductCounter
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@Composable
fun DetailScreen(
    skincareId: Long,
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getSkincareById(skincareId = skincareId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    thumbnail = data.skincare.thumbnail,
                    name = data.skincare.name,
                    desc = data.skincare.desc,
                    price = data.skincare.price,
                    count = data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.skincare, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes thumbnail: Int,
    name: String,
    desc: String,
    price: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var totalPrice by rememberSaveable {
        mutableStateOf(0)
    }
    var orderCount by rememberSaveable {
        mutableStateOf(count)
    }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box(modifier = Modifier) {
                Image(
                    painter = painterResource(id = thumbnail),
                    contentDescription = stringResource(id = R.string.content_desc_product_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.price, price),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .paddingFromBaseline(bottom = 14.dp)
                )

                Text(
                    text = name,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.paddingFromBaseline(bottom = 20.dp)
                )

                Text(
                    text = stringResource(id = R.string.section_description),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .paddingFromBaseline(bottom = 8.dp)
                )

                Text(
                    text = desc,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            ProductCounter(
                skincareId = 1,
                orderCount = orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            totalPrice = price * orderCount
            OrderButton(
                text = stringResource(id = R.string.add_to_cart, totalPrice),
                enabled = orderCount > 0,
                onClick = { onAddToCart(orderCount) }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetSkincareTheme {
        DetailContent(
            thumbnail = R.drawable.product_1,
            name = "SKINTIFIC MSH Niacinamide Brightening Moisturizer Moisture Gel Glowing",
            price = 129000,
            desc = "Skintific MSH Niacinamide Brightening Moisture Gel MSH Niacinamide Brightening Moisture Gel dengan tekstur seringan udara, dapat menyerap dengan cepat dan mengontrol minyak. Diformulasikan dengan Novel MSH Niacinamide ekslusif SKINTIFIC yang dikombinasikan dengan dua bahan pencerah yang ringan dan paling efektif yaitu Alpha Arbutin dan Tranexamic Acid, yang mampu mencerahkan dengan signifikan. MSH Niacinamide terbukti secara klinis 10 kali lebih efektif dibandingkan niacinamide biasa dalam mengurangi hiperpigmentasi, meredakan kemerahan dan memperbaiki elastisitas kulit. Diperkaya dengan Centella Asiatica dan 5X Ceramide, tidak menyebabkan iritasi serta memberikan efek menenangkan kulit sekaligus memperbaiki dan menjaga skin barrier.",
            count = 1,
            onBackClick = {},
            onAddToCart = {},
        )
    }
}