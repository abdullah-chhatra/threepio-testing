package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;

public final class IEditText extends IEditTextBase<IEditText> {

  private IEditText(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forEditText() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  public static final class Builder extends IEditTextBase.Builder<IEditText, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IEditText create(@NonNull InteractionAdapter adapter) {
      return new IEditText(adapter);
    }
  }
}
