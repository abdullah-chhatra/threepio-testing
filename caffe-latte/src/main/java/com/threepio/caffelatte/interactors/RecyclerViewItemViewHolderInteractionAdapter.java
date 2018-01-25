package com.threepio.caffelatte.interactors;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.threepio.caffelatte.matchers.RecyclerViewItemMatcher;
import com.threepio.caffelatte.matchers.RecyclerViewItemViewHolderMatcher;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToHolder;


public class RecyclerViewItemViewHolderInteractionAdapter implements InteractionAdapter {

  private final ViewInteraction parentInteraction;
  private final Matcher<RecyclerView.ViewHolder> itemHolderMatcher;
  private final RecyclerViewItemMatcher itemMatcher;
  private final ViewInteraction itemInteraction;

  RecyclerViewItemViewHolderInteractionAdapter(Matcher<RecyclerView.ViewHolder> itemHolderMatcher, Matcher<View> parentMatcher) {
    this.parentInteraction = onView(parentMatcher);
    this.itemHolderMatcher = itemHolderMatcher;
    this.itemMatcher = new RecyclerViewItemViewHolderMatcher(itemHolderMatcher, parentMatcher);
    this.itemInteraction = onView(itemMatcher);
  }

  @Override
  public Matcher<View> getViewMatcher() {
    return itemMatcher;
  }

  @Override
  public void perform(ViewAction action) {
    parentInteraction.perform(actionOnHolderItem(itemHolderMatcher, action));
  }

  @Override
  public void scrollTo() {
    parentInteraction.perform(scrollToHolder(itemHolderMatcher));
  }

  @Override
  public void match(Matcher<View> matcher) {
    check(matches(matcher));
  }

  @Override
  public void check(ViewAssertion assertion) {
    itemInteraction.check(assertion);
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