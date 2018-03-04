package com.achhatra.threepio.testing.caffelatte.interactors;

import android.support.annotation.NonNull;

public final class IToggleButton extends ICompoundButton<IToggleButton> {

  private IToggleButton(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static final class Builder extends ICompoundButton.Builder<IToggleButton, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IToggleButton create(@NonNull InteractionAdapter adapter) {
      return new IToggleButton(adapter);
    }
  }
}
