package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemMatcher;
import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemViewMatcher;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
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
