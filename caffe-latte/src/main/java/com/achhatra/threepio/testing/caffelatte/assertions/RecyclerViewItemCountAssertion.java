package com.achhatra.threepio.testing.caffelatte.assertions;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import static org.junit.Assert.assertEquals;

public class RecyclerViewItemCountAssertion extends TypeSafeViewAssertion<RecyclerView> {

  private final int expectedItemCount;

  public RecyclerViewItemCountAssertion(int expectedItemCount) {
    super(RecyclerView.class);
    this.expectedItemCount = expectedItemCount;
  }

  @SuppressLint("DefaultLocale")
  @Override
  protected void check(RecyclerView recyclerView) {
    int actualItemCount = recyclerView.getAdapter().getItemCount();

    assertEquals(
      String.format("Expected %d recycler view items, but was found %d.", expectedItemCount, actualItemCount),
      expectedItemCount, actualItemCount);
  }
}
