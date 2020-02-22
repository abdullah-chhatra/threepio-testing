package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;

public final class IButton extends IButtonBase<IButton> {

  private IButton(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forButton() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  public static final class Builder extends IButtonBase.Builder<IButton, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IButton create(@NonNull InteractionAdapter adapter) {
      return new IButton(adapter);
    }
  }
}
