package com.threepio.caffelatte.interactors;


import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public class ISingleItemTypeRecyclerViewBase<IB extends IViewBase.Builder, I extends ISingleItemTypeRecyclerViewBase> extends IRecyclerViewBase<I> {

  private Class<IB> itemBuilder;

  protected ISingleItemTypeRecyclerViewBase(@NonNull Class<IB> itemBuilder, @NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
    this.itemBuilder = itemBuilder;
  }

  public ItemBuilder<IB> item() {
    return item(itemBuilder);
  }

  public static abstract class Builder<
    IB extends IViewBase.Builder,
    I extends ISingleItemTypeRecyclerViewBase,
    B extends ISingleItemTypeRecyclerViewBase.Builder> extends IViewBase.Builder<I, B> {

    private Class<IB> itemBuilder;

    public Builder(InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @SuppressWarnings("unchecked")
    public B withItemBuilder(Class<IB> itemBuilder) {
      this.itemBuilder = itemBuilder;
      return (B) this;
    }

    @Override
    protected I create(@NonNull InteractionAdapter adapter) {
      if(itemBuilder == null) {
        throw new RuntimeException("Item builder is not set for this RecyclerView interactor");
      }
      return create(itemBuilder, adapter);
    }

    abstract protected I create(@NonNull Class<IB> itemBuilder, @NonNull InteractionAdapter adapter);
  }
}
