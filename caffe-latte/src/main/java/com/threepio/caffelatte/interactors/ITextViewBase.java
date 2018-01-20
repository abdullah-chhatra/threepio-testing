package com.threepio.caffelatte.interactors;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

public class ITextViewBase<I extends ITextViewBase> extends IViewBase<I> {

  protected ITextViewBase(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public I hasText(@StringRes int stringId) {
    return match(ViewMatchers.withText(stringId));
  }

  public I hasText(String text) {
    return match(ViewMatchers.withText(text));
  }

  public I hasHint(@StringRes int stringId) {
    return match(ViewMatchers.withHint(stringId));
  }

  public I hasHint(String text) {
    return match(ViewMatchers.withHint(text));
  }

  public I openLink(Matcher<String> linkTextMatcher, Matcher<Uri> uriMatcher) {
    return perform(ViewActions.openLink(linkTextMatcher, uriMatcher));
  }

  public I openLink(Matcher<String> linkTextMatcher) {
    return perform(ViewActions.openLinkWithText(linkTextMatcher));
  }

  public I openLinkWithText(String text) {
    return perform(ViewActions.openLinkWithText(text));
  }

  public I openLinkWithUri(Matcher<Uri> uriMatcher) {
    return perform(ViewActions.openLinkWithUri(uriMatcher));
  }

  public I openLinkWithUri(String uri) {
    return perform(ViewActions.openLinkWithUri(uri));
  }

  public static abstract class Builder<I extends ITextViewBase, B extends Builder> extends IViewBase.Builder<I, B> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    public B withText(@StringRes int stringId) {
      return addMatcher(ViewMatchers.withText(stringId));
    }

    public B withText(@NonNull String text) {
      return addMatcher(ViewMatchers.withText(text));
    }

    public B withHint(@StringRes int stringId) {
      return addMatcher(ViewMatchers.withHint(stringId));
    }

    public B withHint(@NonNull String text) {
      return addMatcher(ViewMatchers.withHint(text));
    }
  }
}
