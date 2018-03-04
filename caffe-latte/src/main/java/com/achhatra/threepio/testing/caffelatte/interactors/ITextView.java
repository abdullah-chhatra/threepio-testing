package com.achhatra.threepio.testing.caffelatte.interactors;


import android.support.annotation.NonNull;

public final class ITextView extends ITextViewBase<ITextView> {

  private ITextView(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public static Builder forTextView() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  public static final class Builder extends ITextViewBase.Builder<ITextView, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected ITextView create(@NonNull InteractionAdapter adapter) {
      return new ITextView(adapter);
    }
  }
}
