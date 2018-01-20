package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;


public final class IRadioButton extends ICompoundButton<IRadioButton> {

  private IRadioButton(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static final class Builder extends ICompoundButton.Builder<IRadioButton, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IRadioButton create(@NonNull InteractionAdapter adapter) {
      return new IRadioButton(adapter);
    }
  }
}
