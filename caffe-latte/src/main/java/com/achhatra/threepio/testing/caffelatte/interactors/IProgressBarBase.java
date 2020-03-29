package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;

public class IProgressBarBase<I extends IProgressBarBase> extends IViewBase<I> {

    IProgressBarBase(@NonNull InteractionAdapter interactionAdapter) {
        super(interactionAdapter);
    }

    public static abstract class Builder<I extends IProgressBarBase, B extends Builder> extends IViewBase.Builder<I, B> {

        public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
            super(adapterFactory);
        }
    }
}
