package com.threepio.caffelatte.matchers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class RecyclerViewItemViewMatcher extends RecyclerViewItemMatcher {

  private final Matcher<View> itemMatcher;

  public RecyclerViewItemViewMatcher(Matcher<View> itemMatcher, Matcher<View> parentMatcher) {
    super(parentMatcher);
    this.itemMatcher = itemMatcher;
  }

  @Override
  protected boolean matchItem(RecyclerView parent, View item) {
    return itemMatcher.matches(item);
  }

  @Override
  public void describeTo(Description description) {

  }
}
