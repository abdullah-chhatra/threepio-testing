package com.achhatra.threepio.testing.caffelatte.matchers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class RecyclerViewItemViewHolderMatcher extends RecyclerViewItemMatcher {

  private final Matcher<RecyclerView.ViewHolder> itemMatcher;

  public RecyclerViewItemViewHolderMatcher(Matcher<RecyclerView.ViewHolder> itemMatcher, Matcher<View> parentMatcher) {
    super(parentMatcher);
    this.itemMatcher = itemMatcher;
  }

  @Override
  protected boolean matchItem(RecyclerView parent, View item) {
    return itemMatcher.matches(parent.getChildViewHolder(item));
  }

  @Override
  public void describeTo(Description description) {

  }
}
