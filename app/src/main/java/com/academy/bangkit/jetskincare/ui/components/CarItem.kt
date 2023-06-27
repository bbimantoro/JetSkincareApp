package com.academy.bangkit.jetskincare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    id: Int,
    thumbnail: Int,
    name: String,
    price: String,
    count: Int,
    onProductCountChanged: (id: Int, count: Int) -> Unit,
) {

    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = thumbnail),
            contentDescription = stringResource(id = R.string.content_desc_product_image),
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f)
        ) {
            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.paddingFromBaseline(bottom = 14.dp)
            )
            Text(
                text = stringResource(id = R.string.price, price),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            ProductCounter(
                modifier = Modifier.align(Alignment.End),
                id = id,
                orderCount = count,
                onProductDecreased = { onProductCountChanged(id, count - 1) },
                onProductIncreased = { onProductCountChanged(id, count + 1) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    JetSkincareTheme {
        CartItem(
            id = 1,
            thumbnail = R.drawable.product_1,
            name = "SKINTIFIC MSH Niacinamide Brightening Moisturizer Moisture Gel Glowing",
            price = "129.000",
            count = 0,
            onProductCountChanged = { _, _ -> },
        )
    }
}