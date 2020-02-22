package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;

public final class IRecyclerView extends IRecyclerViewBase<IRecyclerView> {

  private IRecyclerView(InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forRecyclerView() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  public static final class Builder extends IRecyclerViewBase.Builder<IRecyclerView, Builder> {

    public Builder(InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IRecyclerView create(@NonNull InteractionAdapter adapter) {
      return new IRecyclerView(adapter);
    }
  }
}
