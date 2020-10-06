package com.achhatra.threepio.testing.caffelatte.actions;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import static android.os.SystemClock.currentThreadTimeMillis;
import static android.os.SystemClock.uptimeMillis;


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
    long endTime = uptimeMillis() + waitTillMilliseconds;
    do {
      if (matcher.matches(view)) {
        return;
      }

      uiController.loopMainThreadForAtLeast(50);
    }
    while (uptimeMillis() < endTime);

    throw new PerformException.Builder()
      .withActionDescription(getDescription())
      .withViewDescription(HumanReadables.describe(view))
      .withCause(new TimeoutException())
      .build();
  }
}
