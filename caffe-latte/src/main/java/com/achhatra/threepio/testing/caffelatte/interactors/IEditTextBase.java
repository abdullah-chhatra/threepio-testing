package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import static com.achhatra.threepio.testing.caffelatte.Utils.getString;


public abstract class IEditTextBase<I extends IEditTextBase> extends ITextViewBase<I> {

  protected IEditTextBase(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public I hasErrorText(@StringRes int resourceId) {
    return hasErrorText(getString(resourceId));
  }

  public I hasErrorText(@NonNull String errorText) {
    return match(ViewMatchers.hasErrorText(errorText));
  }

  public I hasInputType(int inputType) {
    return match(ViewMatchers.withInputType(inputType));
  }

  public I typeText(@NonNull String text) {
    return perform(ViewActions.typeText(text));
  }

  public I replaceText(@NonNull String text) {
    return perform(ViewActions.replaceText(text));
  }

  public I clearText() {
    return perform(ViewActions.clearText());
  }

  public I pressImeActionButton() {
    return perform(ViewActions.pressImeActionButton());
  }

  public I closeSoftKeyboard() {
    return perform(ViewActions.closeSoftKeyboard());
  }

  public static abstract class Builder<I extends IEditTextBase, B extends Builder> extends ITextViewBase.Builder<I, B> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    public B withText(@StringRes int resourceId) {
      return addMatcher(ViewMatchers.withText(resourceId));
    }

    public B withText(@NonNull String text) {
      return addMatcher(ViewMatchers.withText(text));
    }

    public B withHint(@StringRes int resourceId) {
      return addMatcher(ViewMatchers.withHint(resourceId));
    }

    public B withHint(@NonNull String hint) {
      return addMatcher(ViewMatchers.withHint(hint));
    }

    public B withInputType(int inputType) {
      return addMatcher(ViewMatchers.withInputType(inputType));
    }
  }
}
