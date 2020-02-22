package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import android.view.View;

import org.hamcrest.Matcher;

public interface InteractionAdapter {

  Matcher<View> getViewMatcher();

  void perform(ViewAction action);

  void scrollTo();

  void match(Matcher<View> matcher);

  void check(ViewAssertion assertion);
}
