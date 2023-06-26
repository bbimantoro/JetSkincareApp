package com.academy.bangkit.jetskincare.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@Composable
fun OrderButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(20),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Order"
            }
    ) {
        Text(text = text, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Preview(showBackground = true)
@Composable
fun OrderButtonPreview() {
    JetSkincareTheme {
        OrderButton("Keranjang", onClick = {})
    }
}