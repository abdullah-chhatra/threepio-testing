package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

public class ViewInteractionAdapter implements InteractionAdapter {

  private final Matcher<View> viewMatcher;

  public ViewInteractionAdapter(@NonNull Matcher<View> matcher) {
    this.viewMatcher = matcher;
  }

  @Override
  public Matcher<View> getViewMatcher() {
    return viewMatcher;
  }

  @Override
  public void perform(ViewAction action) {
    viewInteraction().perform(action);
  }

  @Override
  public void scrollTo() {
    viewInteraction().perform(ViewActions.scrollTo());
  }

  @Override
  public void match(Matcher<View> matcher) {
    check(matches(matcher));
  }

  @Override
  public void check(ViewAssertion assertion) {
    viewInteraction().check(assertion);
  }

  private ViewInteraction viewInteraction() {
    return Espresso.onView(viewMatcher);
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