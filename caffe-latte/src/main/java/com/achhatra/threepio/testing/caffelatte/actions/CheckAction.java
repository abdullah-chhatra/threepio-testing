package com.achhatra.threepio.testing.caffelatte.actions;

import androidx.test.espresso.UiController;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;


public class CheckAction extends TypeSafeViewAction<CompoundButton> {

  private final boolean check;

  public CheckAction(boolean check) {
    super(CompoundButton.class);
    this.check = check;
  }

  @Override
  public Matcher<View> getConstraints() {
    return allOf(super.getConstraints(), isDisplayed());
  }

  @Override
  public String getDescription() {
    return "checks/un-checks a compound button";
  }

  @Override
  protected void performSafely(UiController uiController, CompoundButton view) {
    if (view.isChecked() != check) {
      new GeneralClickAction(
        Tap.SINGLE,
        GeneralLocation.VISIBLE_CENTER,
        Press.FINGER,
        InputDevice.SOURCE_UNKNOWN,
        MotionEvent.BUTTON_PRIMARY).perform(uiController, view);
    }
  }
}
