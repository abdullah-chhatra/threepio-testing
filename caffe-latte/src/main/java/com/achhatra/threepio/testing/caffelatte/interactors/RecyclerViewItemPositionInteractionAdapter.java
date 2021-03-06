package com.achhatra.threepio.testing.caffelatte.interactors;


import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import android.view.View;

import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemMatcher;
import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemPositionMatcher;

import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;

class RecyclerViewItemPositionInteractionAdapter implements InteractionAdapter {

  private final int position;
  private final Matcher<View> parentMatcher;
  private final RecyclerViewItemMatcher itemMatcher;

  public RecyclerViewItemPositionInteractionAdapter(int position, Matcher<View> parentMatcher) {
    this.position = position;
    this.parentMatcher = parentMatcher;
    this.itemMatcher = new RecyclerViewItemPositionMatcher(position, parentMatcher);
  }

  @Override
  public Matcher<View> getViewMatcher() {
    return itemMatcher;
  }

  @Override
  public void perform(ViewAction action) {
    parentInteraction().perform(actionOnItemAtPosition(position, action));
  }

  @Override
  public void scrollTo() {
    parentInteraction().perform(scrollToPosition(position));
  }

  @Override
  public void match(Matcher<View> matcher) {
    itemInteraction().check(matches(matcher));
  }

  @Override
  public void check(ViewAssertion assertion) {
    itemInteraction().check(assertion);
  }

  private ViewInteraction parentInteraction() {
    return onView(parentMatcher);
  }

  private ViewInteraction itemInteraction() {
    return onView(itemMatcher);
  }

  static class Factory implements InteractionAdapterFactory {

    private final int position;
    private final Matcher<View> parentMatcher;

    Factory(int position, Matcher<View> parentMatcher) {
      this.position = position;
      this.parentMatcher = parentMatcher;
    }

    @Override
    public void addMatcher(Matcher<View> matcher) {
      throw new RuntimeException("No additional matchers can be added to view interactor "
        + "for RecycleView item with position");
    }

    @Override
    public InteractionAdapter create() {
      return new RecyclerViewItemPositionInteractionAdapter(position, parentMatcher);
    }
  }
}
