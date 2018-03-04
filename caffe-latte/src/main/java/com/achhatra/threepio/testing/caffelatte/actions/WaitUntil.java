package com.achhatra.threepio.testing.caffelatte.actions;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import static android.os.SystemClock.currentThreadTimeMillis;


public class WaitUntil implements ViewAction {

  private final Matcher<View> matcher;
  private final long waitTillMilliseconds;

  public WaitUntil(Matcher<View> matcher, long waitTillMilliseconds) {
    this.matcher = matcher;
    this.waitTillMilliseconds = waitTillMilliseconds;
  }

  @Override
  public Matcher<View> getConstraints() {
    return ViewMatchers.isAssignableFrom(View.class);
  }

  @Override
  public String getDescription() {
    return "Waits till either view matches the specified matcher or timeout happens";
  }

  @Override
  public void perform(UiController uiController, View view) {
    long endTime = currentThreadTimeMillis() + waitTillMilliseconds;
    do {
      if (matcher.matches(view)) {
        return;
      }

      uiController.loopMainThreadForAtLeast(50);
    }
    while (currentThreadTimeMillis() < endTime);

    throw new PerformException.Builder()
      .withActionDescription(getDescription())
      .withViewDescription(HumanReadables.describe(view))
      .withCause(new TimeoutException())
      .build();
  }
}
