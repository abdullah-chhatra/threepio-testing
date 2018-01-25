package com.threepio.testing;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.threepio.caffelatte.interactors.IButton;
import com.threepio.caffelatte.interactors.ICheckBox;
import com.threepio.caffelatte.interactors.IEditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static com.threepio.caffelatte.interactors.IButton.forButton;
import static com.threepio.caffelatte.interactors.ICheckBox.forCheckBox;
import static com.threepio.caffelatte.interactors.IEditText.forEditText;

@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

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

  private IButton signIn = forButton()
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

    signIn
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

    signIn.isEnabled();
  }
}
