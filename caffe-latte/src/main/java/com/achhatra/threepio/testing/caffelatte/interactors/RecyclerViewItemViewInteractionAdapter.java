package com.achhatra.threepio.testing.caffelatte.interactors;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemMatcher;
import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemViewMatcher;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static org.hamcrest.Matchers.allOf;


public class RecyclerViewItemViewInteractionAdapter implements InteractionAdapter {

  private final Matcher<View> parentMatcher;
  private final RecyclerViewItemMatcher itemMatcher;

  RecyclerViewItemViewInteractionAdapter(Matcher<View> itemMatcher, Matcher<View> parentMatcher) {
    this.parentMatcher = parentMatcher;
    this.itemMatcher = new RecyclerViewItemViewMatcher(itemMatcher, parentMatcher);
  }

  @Override
  public Matcher<View> getViewMatcher() {
    return itemMatcher;
  }

  @Override
  public void perform(ViewAction action) {
    parentInteraction().perform(actionOnItem(itemMatcher, action));
  }

  @Override
  public void scrollTo() {
    parentInteraction().perform(RecyclerViewActions.scrollTo(itemMatcher));
  }

  @Override
  public void match(Matcher<View> matcher) {
    check(matches(matcher));
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

    private final List<Matcher<? super View>> matchers;
    private final Matcher<View> parentMatcher;

    public Factory(Matcher<View> parentMatcher) {
      this.parentMatcher = parentMatcher;
      matchers = new ArrayList<>();
    }

    @Override
    public void addMatcher(Matcher<View> matcher) {
      matchers.add(matcher);
    }

    @Override
    public InteractionAdapter create() {
      return new RecyclerViewItemViewInteractionAdapter(allOf(matchers), parentMatcher);
    }
  }
}
