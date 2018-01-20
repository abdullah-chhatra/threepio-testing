package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;

public final class ICheckBox extends ICompoundButton<ICheckBox> {

  private ICheckBox(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static final class Builder extends ICompoundButton.Builder<ICheckBox, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected ICheckBox create(@NonNull InteractionAdapter adapter) {
      return new ICheckBox(adapter);
    }
  }
}
