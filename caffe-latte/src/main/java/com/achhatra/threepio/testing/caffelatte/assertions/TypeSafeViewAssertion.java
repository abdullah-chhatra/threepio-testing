package com.achhatra.threepio.testing.caffelatte.assertions;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import android.view.View;

import org.junit.Assert;

public abstract class TypeSafeViewAssertion<T extends View> implements ViewAssertion {

  private final Class<T> expectedViewType;

  public TypeSafeViewAssertion(Class<T> expectedViewType) {
    this.expectedViewType = expectedViewType;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException != null) {
      throw noViewFoundException;
    }

    if (expectedViewType.isInstance(view)) {
      check((T) view);
    } else {
      Assert.fail("View is not of type: " + expectedViewType.getName());
    }
  }

  protected abstract void check(T view);
}
