package com.threepio.caffelatte.matchers;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RecyclerViewItemPositionMatcher extends RecyclerViewItemMatcher {

  private final int position;

  public RecyclerViewItemPositionMatcher(int position, Matcher<View> parentMatcher) {
    super(parentMatcher);
    this.position = position;
  }

  @Override
  protected boolean matchItem(RecyclerView parent, View item) {
    return parentHasItemAtPosition(parent, position)
      && parent.findViewHolderForAdapterPosition(position).itemView == item;
  }

  @Override
  public void describeTo(Description description) {
    description
      .appendText(" item at position " + position + " in RecyclerView ")
      .appendDescriptionOf(parentMatcher);
  }

  private boolean parentHasItemAtPosition(RecyclerView parent, int position) {
    RecyclerView.Adapter adapter = parent.getAdapter();
    return adapter != null && adapter.getItemCount() > position;
  }
}
