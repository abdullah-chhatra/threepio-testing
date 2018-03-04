package com.achhatra.threepio.testing.testapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.achhatra.threepio.testing.caffelatte.interactors.IButton;
import com.achhatra.threepio.testing.caffelatte.interactors.ICheckBox;
import com.achhatra.threepio.testing.caffelatte.interactors.IEditText;
import com.threepio.testing.SigninActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static com.achhatra.threepio.testing.caffelatte.interactors.IButton.forButton;
import static com.achhatra.threepio.testing.caffelatte.interactors.ICheckBox.forCheckBox;
import static com.achhatra.threepio.testing.caffelatte.interactors.IEditText.forEditText;

@RunWith(AndroidJUnit4.class)
public class SignInActivityTest_Java {

  @Rule
  public ActivityTestRule<SigninActivity> rule = new ActivityTestRule<>(SigninActivity.class);

  private IEditText userName = forEditText()
    .withId(R.id.user_name)
    .build();

  private IEditText password = forEditText()
    .withId(R.id.password)
    .build();

  private ICheckBox showPassword = forCheckBox()
    .withId(R.id.show_password)
    .build();

  private IButton signInButton = forButton()
    .withId(R.id.sign_in)
    .withText("Sign In")
    .build();

  @Test
  public void testInitialScreen() {
    userName
      .isDisplayed()
      .hasHint("User Email")
      .hasEmptyText()
      .hasInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

    password
      .isDisplayed()
      .hasHint("Password")
      .hasEmptyText()
      .hasInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);

    showPassword
      .isDisplayed()
      .hasText("Show password")
      .isNotChecked();

    signInButton
      .isDisplayed()
      .isNotEnabled();
  }

  @Test
  public void testPasswordToggle() {
    String passwordText = "lkj3474";
    password.typeText(passwordText);

    showPassword.check();
    password.hasInputType(TYPE_CLASS_TEXT);

    showPassword.uncheck();
    password.hasInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
  }

  @Test
  public void testEnableSignInOnValidUserDetails() {
    userName.typeText("abcd@email.com");
    password.typeText("E948fkfj");

    signInButton.isEnabled();
  }
}
