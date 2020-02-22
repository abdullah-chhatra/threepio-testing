package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemMatcher;
import com.achhatra.threepio.testing.caffelatte.matchers.RecyclerViewItemViewHolderMatcher;

import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder;


public class RecyclerViewItemViewHolderInteractionAdapter implements InteractionAdapter {

  private final Matcher<View> parentMatcher;
  private final Matcher<RecyclerView.ViewHolder> itemHolderMatcher;
  private final RecyclerViewItemMatcher itemMatcher;

  RecyclerViewItemViewHolderInteractionAdapter(Matcher<RecyclerView.ViewHolder> itemHolderMatcher, Matcher<View> parentMatcher) {
    this.parentMatcher = parentMatcher;
    this.itemHolderMatcher = itemHolderMatcher;
    this.itemMatcher = new RecyclerViewItemViewHolderMatcher(itemHolderMatcher, parentMatcher);
  }

  @Override
  public Matcher<View> getViewMatcher() {
    return itemMatcher;
  }

  @Override
  public void perform(ViewAction action) {
    parentInteraction().perform(actionOnHolderItem(itemHolderMatcher, action));
  }

  @Override
  public void scrollTo() {
    parentInteraction().perform(scrollToHolder(itemHolderMatcher));
  }

  @Override
  public void match(Matcher<View> matcher) {
    check(matches(matcher));
  }

  @Override
  public void check(ViewAssertion assertion) {
    itemInteraction().check(assertion);
  }

  private ViewInteraction parentInteraction() {
    return onView(parentMatcher);
  }

  private ViewInteraction itemInteraction() {
    return onView(itemMatcher);
  }

  static class Factory implements InteractionAdapterFactory {

    private final Matcher<RecyclerView.ViewHolder> itemHolderMatcher;
    private final Matcher<View> parentMatcher;

    Factory(Matcher<RecyclerView.ViewHolder> itemHolderMatcher, Matcher<View> parentMatcher) {
      this.itemHolderMatcher = itemHolderMatcher;
      this.parentMatcher = parentMatcher;
    }

    @Override
    public void addMatcher(Matcher<View> matcher) {
      throw new RuntimeException("No additional matchers can be added to view interactor "
        + "for RecycleView item holder");
    }

    @Override
    public InteractionAdapter create() {
      return new RecyclerViewItemViewHolderInteractionAdapter(itemHolderMatcher, parentMatcher);
    }
  }
}
