package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;


public final class IRadioButton extends ICompoundButton<IRadioButton> {

  private IRadioButton(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forRadioButton() {
    return new Builder(new ViewInteractionAdapter.Factory());
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
