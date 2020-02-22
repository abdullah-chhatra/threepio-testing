package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;

public class ISingleItemTypeRecyclerView<IB extends IViewBase.Builder> extends ISingleItemTypeRecyclerViewBase<IB, ISingleItemTypeRecyclerView> {

  private ISingleItemTypeRecyclerView(@NonNull Class<IB> itemBuilder, @NonNull InteractionAdapter interactionAdapter) {
    super(itemBuilder, interactionAdapter);
  }

  public static <IB extends IViewBase.Builder> Builder<IB> forRecyclerView() {
    return new Builder<>(new ViewInteractionAdapter.Factory());
  }

  public static class Builder<IB extends IViewBase.Builder> extends ISingleItemTypeRecyclerViewBase.Builder<IB, ISingleItemTypeRecyclerView<IB>, Builder<IB>> {

    private Builder(InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected ISingleItemTypeRecyclerView<IB> create(@NonNull Class<IB> itemBuilder, @NonNull InteractionAdapter adapter) {
      return new ISingleItemTypeRecyclerView<>(itemBuilder, adapter);
    }
  }
}
