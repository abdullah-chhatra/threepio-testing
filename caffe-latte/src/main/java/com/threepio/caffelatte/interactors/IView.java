package com.threepio.caffelatte.interactors;


import android.support.annotation.NonNull;

public final class IView extends IViewBase<IView> {

  private IView(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forView() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  public static final class Builder extends IViewBase.Builder<IView, IView.Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected IView create(@NonNull InteractionAdapter adapter) {
      return new IView(adapter);
    }
  }
}