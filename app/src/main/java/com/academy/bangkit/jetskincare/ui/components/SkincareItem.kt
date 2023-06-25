package com.academy.bangkit.jetskincare.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@Composable
fun SkincareItem(
    image: Int,
    name: String, price: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.width(140.dp),
    ) {
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(id = R.string.content_desc_product_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.paddingFromBaseline(bottom = 12.dp)
                )
                Text(
                    text = stringResource(id = R.string.price, price),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkincareItemPreview() {
    JetSkincareTheme {
        SkincareItem(
            image = R.drawable.product_1,
            "SKINTIFIC MSH Niacinamide Brightening Moisturizer Moisture Gel Glowing",
            "24.000"
        )
    }
}