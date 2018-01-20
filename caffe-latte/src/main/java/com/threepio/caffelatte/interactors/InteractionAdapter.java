package com.threepio.caffelatte.interactors;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.view.View;

import org.hamcrest.Matcher;

public interface InteractionAdapter {

  Matcher<View> getViewMatcher();

  void perform(ViewAction action);

  void scrollTo();

  void match(Matcher<View> matcher);

  void check(ViewAssertion assertion);
}
