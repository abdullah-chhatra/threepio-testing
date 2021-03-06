package com.achhatra.threepio.testing.caffelatte.interactors;


import androidx.annotation.NonNull;

public abstract class IButtonBase<I extends IButtonBase> extends IEditTextBase<I> {

  protected IButtonBase(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static abstract class Builder<I extends IButtonBase, B extends Builder> extends ITextViewBase.Builder<I, B> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }
  }
}
