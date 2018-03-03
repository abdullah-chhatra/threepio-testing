package com.threepio.caffelatte.interactors;


import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import com.threepio.caffelatte.matchers.RecyclerViewItemMatcher;
import com.threepio.caffelatte.matchers.RecyclerViewItemPositionMatcher;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;

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
