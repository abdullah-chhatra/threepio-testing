package com.achhatra.threepio.testing.caffelatte.matchers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class HasChildAtPositionMatcher extends BoundedMatcher<View, ViewGroup> {

  private final Matcher<View> childMatcher;
  private final int position;

  public HasChildAtPositionMatcher(Matcher<View> childMatcher, int childPosition) {
    super(ViewGroup.class);
    this.childMatcher = childMatcher;
    this.position = childPosition;
  }

  @Override
  protected boolean matchesSafely(ViewGroup group) {
    return group.getChildCount() >= position + 1 &&
      childMatcher.matches(group.getChildAt(position));
  }

  @Override
  public void describeTo(Description description) {
    description.appendText(" has child at position " + position + " ");
    childMatcher.describeTo(description);
  }
}
