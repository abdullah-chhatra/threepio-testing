package com.threepio.caffelatte.interactors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.threepio.caffelatte.assertions.RecyclerViewItemCountAssertion;

import org.hamcrest.Matcher;

public class IRecyclerViewBase<I extends IRecyclerViewBase> extends IViewBase<I> {

  protected IRecyclerViewBase(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  public I hasItemCount(int count) {
    return check(new RecyclerViewItemCountAssertion(count));
  }

  public <B extends IViewBase.Builder> ItemBuilder<B> item(@NonNull Class<B> builder) {
    return new ItemBuilder<>(builder, getViewMatcher());
  }

  public static abstract class Builder<I extends IRecyclerViewBase, B extends Builder> extends IViewBase.Builder<I, B> {

    public Builder(InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }
  }

  public static class ItemBuilder<B extends IViewBase.Builder> {

    private final Class<B> builder;
    private final Matcher<View> parentMatcher;

    private ItemBuilder(@NonNull Class<B> builder, @NonNull Matcher<View> parentMatcher) {
      this.builder = builder;
      this.parentMatcher = parentMatcher;
    }

    public B atPosition(int position) {
      return createBuilder(new RecyclerViewItemPositionInteractionAdapter.Factory(position, parentMatcher));
    }

    public B hasView() {
      return createBuilder(new RecyclerViewItemViewInteractionAdapter.Factory(parentMatcher));
    }

    public B hasViewHolder(Matcher<RecyclerView.ViewHolder> holderMatcher) {
      return createBuilder(new RecyclerViewItemViewHolderInteractionAdapter.Factory(holderMatcher, parentMatcher));
    }

    private B createBuilder(InteractionAdapterFactory factory) {
      try {
        return builder.getConstructor(InteractionAdapterFactory.class).newInstance(factory);
      } catch (Exception e) {
        throw new RuntimeException("Exception while creating builder for RecyclerView item interactor", e);
      }
    }
  }
}
