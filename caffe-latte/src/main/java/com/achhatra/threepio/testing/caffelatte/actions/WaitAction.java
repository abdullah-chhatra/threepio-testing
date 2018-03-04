package com.achhatra.threepio.testing.caffelatte.actions;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class WaitAction implements ViewAction {

  private long waitTimeInMilliseconds;

  public WaitAction(long waitTimeInMilliseconds) {
    this.waitTimeInMilliseconds = waitTimeInMilliseconds;
  }

  @Override
  public Matcher<View> getConstraints() {
    return isAssignableFrom(View.class);
  }

  @Override
  public String getDescription() {
    return "Waits for specified number of milliseconds";
  }

  @Override
  public void perform(UiController uiController, View view) {
    uiController.loopMainThreadForAtLeast(waitTimeInMilliseconds);
  }
}
