package com.achhatra.threepio.testing.testapp


import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.achhatra.threepio.testing.caffelatte.interactors.IButton
import com.achhatra.threepio.testing.caffelatte.interactors.ICheckBox
import com.achhatra.threepio.testing.caffelatte.interactors.IEditText
import com.threepio.testing.SigninActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import com.achhatra.threepio.testing.caffelatte.interactors.IButton.forButton
import com.achhatra.threepio.testing.caffelatte.interactors.ICheckBox.forCheckBox
import com.achhatra.threepio.testing.caffelatte.interactors.IEditText.forEditText

@Suppress("ClassName")
@RunWith(AndroidJUnit4::class)
class SignInActivityTest_Kotlin {

  @get:Rule
  var rule = ActivityTestRule(SigninActivity::class.java)

  private val userName = forEditText()
    .withId(R.id.user_name)
    .build()

  private val password = forEditText()
    .withId(R.id.password)
    .build()

  private val showPassword = forCheckBox()
    .withId(R.id.show_password)
    .build()

  private val signInButton = forButton()
    .withId(R.id.sign_in)
    .withText("Sign In")
    .build()

  @Test
  fun testInitialScreen() {
    userName
      .isDisplayed
      .hasHint("User Email")
      .hasEmptyText()
      .hasInputType(TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_EMAIL_ADDRESS)

    password
      .isDisplayed
      .hasHint("Password")
      .hasEmptyText()
      .hasInputType(TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD)

    showPassword
      .isDisplayed
      .hasText("Show password")
      .isNotChecked

    signInButton
      .isDisplayed
      .isNotEnabled
  }

  @Test
  fun testPasswordToggle() {
    val passwordText = "lkj3474"
    password.typeText(passwordText)

    showPassword.check()
    password.hasInputType(TYPE_CLASS_TEXT)

    showPassword.uncheck()
    password.hasInputType(TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD)
  }

  @Test
  fun testEnableSignInOnValidUserDetails() {
    userName.typeText("abcd@email.com")
    password.typeText("E948fkfj")

    signInButton.isEnabled
  }
}
