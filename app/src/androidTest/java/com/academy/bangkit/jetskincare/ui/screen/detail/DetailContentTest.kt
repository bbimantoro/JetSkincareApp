package com.academy.bangkit.jetskincare.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.academy.bangkit.jetskincare.R
import com.academy.bangkit.jetskincare.model.OrderSkincare
import com.academy.bangkit.jetskincare.model.Skincare
import com.academy.bangkit.jetskincare.utils.onNodeWithStringId
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderSkincare = OrderSkincare(
        skincare = Skincare(
            6,
            R.drawable.product_6,
            "SKINTIFIC 10% Niacinamide Brightening Serum 20ml-Whitening 【BPOM】",
            "Serum pencerah yang mengandung 10% Niacinamide, yang sama dengan niacinamide pada SK-II, Arbutin, Ceramide, and Centella Asiatica. Dapat mencerahkan dengan cepat, menghilangkan bekas jerawat dan bekas terbakar sinar matahari dalam 7 hari. Niacinamide yang digunakan adalah Niacinamide paling terbaik di dunia, Royal DSM Niacinamide, yang dapat melindungi skin barrier dan mencerahkan secara mendalam.",
            119000
        ),
        count = 0
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            JetSkincareTheme {
                DetailContent(
                    thumbnail = fakeOrderSkincare.skincare.thumbnail,
                    name = fakeOrderSkincare.skincare.name,
                    desc = fakeOrderSkincare.skincare.desc,
                    price = fakeOrderSkincare.skincare.price,
                    count = fakeOrderSkincare.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContentIsDisplayed() {
        composeTestRule.onNodeWithText(fakeOrderSkincare.skincare.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.price,
                fakeOrderSkincare.skincare.price
            ),
        ).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Add to cart").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.counter_increase)
            .performClick()
        composeTestRule.onNodeWithContentDescription("Add to cart").assertIsEnabled()

    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.counter_increase).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.counter_decrease).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}