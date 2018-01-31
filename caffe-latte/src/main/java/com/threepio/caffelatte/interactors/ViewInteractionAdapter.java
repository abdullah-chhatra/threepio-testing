package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

public class ViewInteractionAdapter implements InteractionAdapter {

  private final ViewInteraction viewInteraction;
  private final Matcher<View> viewMatcher;

  public ViewInteractionAdapter(@NonNull Matcher<View> matcher) {
    this.viewMatcher = matcher;
    this.viewInteraction = Espresso.onView(matcher);
  }

  @Override
  public Matcher<View> getViewMatcher() {
    return viewMatcher;
  }

  @Override
  public void perform(ViewAction action) {
    viewInteraction.perform(action);
  }

  @Override
  public void scrollTo() {
    viewInteraction.perform(ViewActions.scrollTo());
  }

  @Override
  public void match(Matcher<View> matcher) {
    check(matches(matcher));
  }

  @Override
  public void check(ViewAssertion assertion) {
    viewInteraction.check(assertion);
  }

  public static class Factory implements InteractionAdapterFactory {
    private final List<Matcher<? super View>> matchers;

    public Factory() {
      matchers = new ArrayList<>();
    }

    @Override
    public void addMatcher(Matcher<View> matcher) {
      matchers.add(matcher);
    }

    @Override
    public InteractionAdapter create() {
      return new ViewInteractionAdapter(allOf(matchers));
    }
  }
}