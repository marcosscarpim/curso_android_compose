package org.venturus.br.testing

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.venturus.br.testing.math.Calculator

class MainScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private val calculator = Calculator()

    @Test
    fun test_AllComponentsAreVisible() {
        composeTestRule.setContent { MainScreen(calculator) }

        composeTestRule.onNodeWithContentDescription("First Number").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Second Number").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Sub").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Sum").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Divide").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Result").assertExists()
    }

    @Test
    fun test_checkSumButtonIsWorking() {
        val first = "2"
        val second = "3"
        val assertText = "5"

        composeTestRule.setContent { MainScreen(calculator) }

        composeTestRule.onNodeWithContentDescription("First Number").performTextInput(first)
        composeTestRule.onNodeWithContentDescription("Second Number").performTextInput(second)
        composeTestRule.onNodeWithContentDescription("Sum").performClick()
        composeTestRule.onNodeWithContentDescription("Result").assertTextEquals(assertText)
    }

    @Test
    fun test_checkSubButtonIsWorking() {
        val first = "2"
        val second = "3"
        val assertText = "-1"

        composeTestRule.setContent { MainScreen(calculator) }

        composeTestRule.onNodeWithContentDescription("First Number").performTextInput(first)
        composeTestRule.onNodeWithContentDescription("Second Number").performTextInput(second)
        composeTestRule.onNodeWithContentDescription("Sub").performClick()
        composeTestRule.onNodeWithContentDescription("Result").assertTextEquals(assertText)
    }

    @Test
    fun test_checkDivButtonIsWorking() {
        val first = "1"
        val second = "2"
        val assertText = "0.5"

        composeTestRule.setContent { MainScreen(calculator) }

        composeTestRule.onNodeWithContentDescription("First Number").performTextInput(first)
        composeTestRule.onNodeWithContentDescription("Second Number").performTextInput(second)
        composeTestRule.onNodeWithContentDescription("Divide").performClick()
        composeTestRule.onNodeWithContentDescription("Result").assertTextEquals(assertText)
    }
}