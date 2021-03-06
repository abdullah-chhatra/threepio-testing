package com.achhatra.threepio.testing.caffelatte.interactors;

import android.view.View;

import org.hamcrest.Matcher;

public interface InteractionAdapterFactory {

  void addMatcher(Matcher<View> matcher);

  InteractionAdapter create();
}
