package com.academy.bangkit.jetskincare.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@Composable
fun OrderButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(20),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Add to cart"
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