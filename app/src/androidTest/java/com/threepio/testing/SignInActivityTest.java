package com.threepio.testing;

import android.support.test.runner.AndroidJUnit4;
import android.widget.RadioButton;

import com.threepio.caffelatte.interactors.IButton;
import com.threepio.caffelatte.interactors.ICheckBox;
import com.threepio.caffelatte.interactors.IEditText;
import com.threepio.caffelatte.interactors.IRadioButton;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.threepio.caffelatte.interactors.IButton.forButton;
import static com.threepio.caffelatte.interactors.ICheckBox.forCheckBox;
import static com.threepio.caffelatte.interactors.IEditText.forEditText;

@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

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
    .build();

  @Test
  void testInitialScreen() {
    userName.isDisplayed().hasHint("User Name");
    password.isDisplayed().hasHint("Password");

    showPassword.isDisplayed().isNotChecked();
    signIn.isDisplayed().isNotEnabled();

  }
}
