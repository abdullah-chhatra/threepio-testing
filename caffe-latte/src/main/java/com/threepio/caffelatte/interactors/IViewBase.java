package com.threepio.caffelatte.interactors;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.threepio.caffelatte.actions.WaitAction;
import com.threepio.caffelatte.actions.WaitUntil;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.GONE;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.INVISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public abstract class IViewBase<I extends IViewBase> {

  protected final InteractionAdapter interactionAdapter;

  protected IViewBase(@NonNull InteractionAdapter interactionAdapter) {
    this.interactionAdapter = interactionAdapter;
  }

  public Matcher<View> getViewMatcher() {
    return interactionAdapter.getViewMatcher();
  }

  //region Actions
  public I click() {
    return perform(ViewActions.click());
  }

  public I click(ViewAction rollbackAction) {
    return perform(ViewActions.click(rollbackAction));
  }

  public I doubleClick() {
    return perform(ViewActions.doubleClick());
  }

  public I longClick() {
    return perform(ViewActions.longClick());
  }

  public I swipeUp() {
    return perform(ViewActions.swipeUp());
  }

  public I swipeDown() {
    return perform(ViewActions.swipeDown());
  }

  public I swipeLeft() {
    return perform(ViewActions.swipeLeft());
  }

  public I swipeRight() {
    return perform(ViewActions.swipeRight());
  }

  public I waitFor(long milliseconds) {
    return perform(actionWithAssertions(new WaitAction(milliseconds)));
  }

  public I waitUntil(Matcher<View> matcher, long timeoutMilliseconds) {
    return perform(actionWithAssertions(new WaitUntil(matcher, timeoutMilliseconds)));
  }

  @SuppressWarnings("unchecked")
  public I perform(@NonNull ViewAction action) {
    interactionAdapter.perform(action);
    return (I) this;
  }

  @SuppressWarnings("unchecked")
  public I scrollTo() {
    interactionAdapter.scrollTo();
    return (I) this;
  }
  //endregion

  //region Assertions
  public I hasId(@IdRes int id) {
    return match(ViewMatchers.withId(id));
  }

  public I hasTagKey(int key) {
    return match(ViewMatchers.withTagKey(key));
  }

  public I hasTagKey(int key, Matcher<Object> valueMatcher) {
    return match(ViewMatchers.withTagKey(key, valueMatcher));
  }

  public I hasTagKey(int key, Object withValue) {
    return hasTagKey(key, is(withValue));
  }

  public I hasTagValue(Matcher<Object> valueMatcher) {
    return match(ViewMatchers.withTagValue(valueMatcher));
  }

  public I hasTagValue(Object value) {
    return hasTagValue(is(value));
  }

  public I isAssignableFrom(Class<? extends View> clazz) {
    return match(ViewMatchers.isAssignableFrom(clazz));
  }

  public I isRoot() {
    return match(ViewMatchers.isRoot());
  }

  public I isDisplayed() {
    return match(ViewMatchers.isDisplayed());
  }

  public I isNotDisplayed() {
    return match(not(ViewMatchers.isDisplayed()));
  }

  public I isCompletelyDisplayed() {
    return match(ViewMatchers.isCompletelyDisplayed());
  }

  public I isDisplayedAtLeast(int areaInPercent) {
    return match(ViewMatchers.isCompletelyDisplayed());
  }

  public I hasEffectiveVisibility(ViewMatchers.Visibility visibility) {
    return match(ViewMatchers.withEffectiveVisibility(visibility));
  }

  public I isGone() {
    return hasEffectiveVisibility(GONE);
  }

  public I isVisible() {
    return hasEffectiveVisibility(VISIBLE);
  }

  public I isInvisible() {
    return hasEffectiveVisibility(INVISIBLE);
  }

  public I isEnabled() {
    return match(ViewMatchers.isEnabled());
  }

  public I isNotEnabled() {
    return match(not(ViewMatchers.isEnabled()));
  }

  public I isFocusable() {
    return match(ViewMatchers.isFocusable());
  }

  public I isClickable() {
    return match(ViewMatchers.isClickable());
  }

  public I isNotClickable() {
    return match(not(ViewMatchers.isClickable()));
  }

  public I isSelected() {
    return match(ViewMatchers.isSelected());
  }

  public I hasContentDescription(@StringRes int resourceId) {
    return match(withContentDescription(resourceId));
  }

  public I hasContentDescription(@NonNull String text) {
    return match(withContentDescription(text));
  }

  public I hasContentDescription() {
    return match(ViewMatchers.hasContentDescription());
  }

  public I hasBackground(@DrawableRes int drawableId) {
    return match(ViewMatchers.hasBackground(drawableId));
  }

  public I hasFocus() {
    return match(ViewMatchers.hasFocus());
  }

  public I hasSibling(Matcher<View> siblingMatcher) {
    return match(ViewMatchers.hasSibling(siblingMatcher));
  }

  public I hasSibling(IViewBase<?> sibling) {
    return hasSibling(sibling.getViewMatcher());
  }

  public I isChildOf(@NonNull Matcher<View> parentMatcher) {
    return match(withParent(parentMatcher));
  }

  public I isChildOf(@NonNull IViewGroupBase<?> parent) {
    return isChildOf(parent.getViewMatcher());
  }

  public I isChildOf(@NonNull Matcher<View> parentMatcher, int atPosition) {
    return match(allOf(
      ViewMatchers.withParent(parentMatcher),
      ViewMatchers.withParentIndex(atPosition)));
  }

  public I isChildOf(@NonNull IViewGroupBase<?> parent, int atPosition) {
    return isChildOf(parent.getViewMatcher(), atPosition);
  }

  public I isDescendantOf(@NonNull Matcher<View> ancestorMatcher) {
    return match(ViewMatchers.isDescendantOfA(ancestorMatcher));
  }

  public I isDescendantOf(@NonNull IViewGroupBase<?> ancestor) {
    return isDescendantOf(ancestor.getViewMatcher());
  }

  @SuppressWarnings("unchecked")
  public I match(@NonNull Matcher<View> matcher) {
    interactionAdapter.match(matcher);
    return (I) this;
  }

  @SuppressWarnings("unchecked")
  public I check(@NonNull ViewAssertion assertion) {
    interactionAdapter.check(assertion);
    return (I) this;
  }
  //endregion

  public abstract static class Builder<I extends IViewBase, B extends Builder> {

    protected final InteractionAdapterFactory adapterFactory;

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      this.adapterFactory = adapterFactory;
    }

    public B withId(@IdRes int id) {
      return addMatcher(ViewMatchers.withId(id));
    }

    public B withTagKey(int key) {
      return addMatcher(ViewMatchers.withTagKey(key));
    }

    public B withTagKey(int key, Matcher<Object> valueMatcher) {
      return addMatcher(ViewMatchers.withTagKey(key, valueMatcher));
    }

    public B withTagKey(int key, Object withValue) {
      return withTagKey(key, is(withValue));
    }

    public B withTagValue(Matcher<Object> valueMatcher) {
      return addMatcher(ViewMatchers.withTagValue(valueMatcher));
    }

    public B isAssignableFrom(Class<? extends View> clazz) {
      return addMatcher(ViewMatchers.isAssignableFrom(clazz));
    }

    public B isRoot() {
      return addMatcher(ViewMatchers.isRoot());
    }

    public B withContentDescription() {
      return addMatcher(ViewMatchers.hasContentDescription());
    }

    public B withContentDescription(@StringRes int stringId) {
      return addMatcher(ViewMatchers.withContentDescription(stringId));
    }

    public B withContentDescription(@NonNull String text) {
      return addMatcher(ViewMatchers.withContentDescription(text));
    }

    public B withSibling(Matcher<View> siblingMatcher) {
      return addMatcher(ViewMatchers.hasSibling(siblingMatcher));
    }

    public B withSibling(IViewBase<?> sibling) {
      return addMatcher(sibling.getViewMatcher());
    }

    public B withParent(@NonNull IViewGroupBase<?> parent) {
      return withParent(parent.getViewMatcher());
    }

    public B withParent(@NonNull Matcher<View> parentMatcher) {
      return addMatcher(ViewMatchers.withParent(parentMatcher));
    }

    public B withParent(@NonNull IViewGroupBase<?> parent, int beingChildAtPosition) {
      return withParent(parent.getViewMatcher(), beingChildAtPosition);
    }

    public B withParent(@NonNull Matcher<View> parentMatcher, int beingChildAtPosition) {
      return addMatcher(
        allOf(
          ViewMatchers.withParent(parentMatcher),
          ViewMatchers.withParentIndex(beingChildAtPosition)));
    }

    @SuppressWarnings("unchecked")
    public B addMatcher(@NonNull Matcher<View> matcher) {
      adapterFactory.addMatcher(matcher);
      return (B) this;
    }

    public I build() {
      return create(adapterFactory.create());
    }

    public I get() {
      return build();
    }

    abstract protected I create(@NonNull InteractionAdapter adapter);
  }
}
