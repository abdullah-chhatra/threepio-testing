package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;

public final class ISwitch extends ICompoundButton<ISwitch> {

  private ISwitch(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static final class Builder extends ICompoundButton.Builder<ISwitch, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected ISwitch create(@NonNull InteractionAdapter adapter) {
      return new ISwitch(adapter);
    }
  }
}
