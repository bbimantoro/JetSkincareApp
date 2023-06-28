package com.academy.bangkit.jetskincare.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        })
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    JetSkincareTheme {
        TopAppBar(title = stringResource(id = R.string.menu_cart))
    }
}