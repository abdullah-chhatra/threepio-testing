package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.threepio.caffelatte.matchers.HasChildAtPositionMatcher;

import org.hamcrest.Matcher;

public class IViewGroupBase<I extends IViewGroupBase> extends IViewBase<I> {

  protected IViewGroupBase(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public I hasChildCount(int count) {
    return match(ViewMatchers.hasChildCount(count));
  }

  public I hasMinimumChildCount(int count) {
    return match(ViewMatchers.hasMinimumChildCount(count));
  }

  public I hasChild(@NonNull IViewBase<?> child) {
    return hasChild(child.getViewMatcher());
  }

  public I hasChild(@NonNull Matcher<View> childMatcher) {
    return match(ViewMatchers.withChild(childMatcher));
  }

  public I hasChild(@NonNull IViewBase<?> child, int atPosition) {
    return hasChild(child.getViewMatcher(), atPosition);
  }

  public I hasChild(@NonNull Matcher<View> childMatcher, int atPosition) {
    return match(new HasChildAtPositionMatcher(childMatcher, atPosition));
  }

  public I hasDescendant(@NonNull IViewBase<?> descendant) {
    return hasDescendant(descendant.getViewMatcher());
  }

  public I hasDescendant(@NonNull Matcher<View> descendantMatcher) {
    return match(ViewMatchers.hasDescendant(descendantMatcher));
  }

  public static abstract class Builder<I extends IViewGroupBase, B extends Builder> extends IViewBase.Builder<I, B> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    public B withChild(@NonNull IViewBase<?> child) {
      return withChild(child.getViewMatcher());
    }

    public B withChild(@NonNull Matcher<View> childMatcher) {
      return addMatcher(ViewMatchers.withChild(childMatcher));
    }

    public B withChild(@NonNull IViewBase<?> child, int atPosition) {
      return withChild(child.getViewMatcher(), atPosition);
    }

    public B withChild(@NonNull Matcher<View> childMatcher, int atPosition) {
      return addMatcher(new HasChildAtPositionMatcher(childMatcher, atPosition));
    }

    public B withDescendant(@NonNull IViewBase<?> child) {
      return withDescendant(child.getViewMatcher());
    }

    public B withDescendant(@NonNull Matcher<View> childMatcher) {
      return addMatcher(ViewMatchers.hasDescendant(childMatcher));
    }
  }
}
