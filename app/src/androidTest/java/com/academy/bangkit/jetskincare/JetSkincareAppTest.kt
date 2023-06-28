package com.academy.bangkit.jetskincare

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.academy.bangkit.jetskincare.model.FakeSkincareDataSource
import com.academy.bangkit.jetskincare.ui.navigation.Screen
import com.academy.bangkit.jetskincare.ui.theme.JetSkincareTheme
import com.academy.bangkit.jetskincare.utils.assertCurrentRouteName
import com.academy.bangkit.jetskincare.utils.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetSkincareAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            JetSkincareTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetSkincareApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("SkincareList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(FakeSkincareDataSource.dummySkincare[9].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailSkincare.route)
        composeTestRule.onNodeWithText(FakeSkincareDataSource.dummySkincare[9].name)
            .assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNav_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("SkincareList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(FakeSkincareDataSource.dummySkincare[9].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailSkincare.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back))
            .performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeSkincareDataSource.dummySkincare[4].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailSkincare.route)
        composeTestRule.onNodeWithStringId(R.string.counter_increase).performClick()
        composeTestRule.onNodeWithContentDescription("Add to cart").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}