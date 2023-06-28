package com.academy.bangkit.jetskincare.ui.screen.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.ui.components.TopAppBar
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = stringResource(id = R.string.menu_profile))

        Image(
            painter = painterResource(id = R.drawable.me),
            contentDescription = stringResource(id = R.string.content_desc_profile_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(2.dp, Color.LightGray, CircleShape)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
                .size(150.dp)

        )

        Text(
            text = stringResource(id = R.string.name),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp, bottom = 8.dp)
        )

        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetSkincareTheme {
        ProfileScreen()
    }
}