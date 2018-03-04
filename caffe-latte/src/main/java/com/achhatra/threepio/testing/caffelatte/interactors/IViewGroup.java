package com.achhatra.threepio.testing.caffelatte.interactors;

import android.support.annotation.NonNull;

public final class IViewGroup extends IViewGroupBase<IViewGroup> {

  private IViewGroup(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forViewGroup() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  public static final class Builder extends IViewGroupBase.Builder<IViewGroup, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IViewGroup create(@NonNull InteractionAdapter adapter) {
      return new IViewGroup(adapter);
    }
  }
}