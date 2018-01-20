package com.threepio.caffelatte.actions;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import org.hamcrest.Matcher;

public abstract class TypeSafeViewAction<T extends View> implements ViewAction {

  private final Class<? extends T> expectedType;

  public TypeSafeViewAction(Class<? extends T> expectedType) {
    this.expectedType = expectedType;
  }

  @Override
  public Matcher<View> getConstraints() {
    return ViewMatchers.isAssignableFrom(expectedType);
  }

  @Override
  public String getDescription() {
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void perform(UiController uiController, View view) {
    performSafely(uiController, (T) view);
  }

  protected abstract void performSafely(UiController uiController, T view);
}
