package com.achhatra.threepio.testing.caffelatte.matchers;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public abstract class RecyclerViewItemMatcher extends TypeSafeMatcher<View> {

  protected final Matcher<View> parentMatcher;

  public RecyclerViewItemMatcher(Matcher<View> parentMatcher) {
    this.parentMatcher = parentMatcher;
  }

  @Override
  protected boolean matchesSafely(View item) {
    RecyclerView parent = getParentRecyclerView(item);
    if (parent != null && parentMatcher.matches(parent)) {
      return matchItem(parent, item);
    }
    return false;
  }

  protected abstract boolean matchItem(RecyclerView parent, View item);

  private RecyclerView getParentRecyclerView(View item) {
    ViewParent parent = item.getParent();
    if (parent != null && parent instanceof RecyclerView) {
      return (RecyclerView) parent;
    }
    return null;
  }
}
