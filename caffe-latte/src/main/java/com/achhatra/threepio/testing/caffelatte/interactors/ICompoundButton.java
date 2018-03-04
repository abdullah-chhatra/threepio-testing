package com.achhatra.threepio.testing.caffelatte.interactors;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.ViewMatchers;

import com.achhatra.threepio.testing.caffelatte.actions.CheckAction;


public abstract class ICompoundButton<I extends ICompoundButton> extends IButtonBase<I> {

  protected ICompoundButton(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public I isChecked() {
    return match(ViewMatchers.isChecked());
  }

  public I isNotChecked() {
    return match(ViewMatchers.isNotChecked());
  }

  public I check() {
    return perform(new CheckAction(true));
  }

  public I uncheck() {
    return perform(new CheckAction(false));
  }

  public static abstract class Builder<I extends ICompoundButton, B extends Builder> extends IButtonBase.Builder<I, B> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }
  }
}
