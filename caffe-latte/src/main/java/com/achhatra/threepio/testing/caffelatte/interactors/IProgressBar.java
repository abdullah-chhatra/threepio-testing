package com.achhatra.threepio.testing.caffelatte.interactors;

import androidx.annotation.NonNull;

public class IProgressBar extends IProgressBarBase<IProgressBar> {

    private IProgressBar(@NonNull InteractionAdapter interactionAdapter) {
        super(interactionAdapter);
    }

    public static Builder forProgressBar() {
        return new Builder(new ViewInteractionAdapter.Factory());
    }

    public static class Builder extends IProgressBarBase.Builder<IProgressBar, Builder> {

        private Builder(@NonNull InteractionAdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        @Override
        protected IProgressBar create(@NonNull InteractionAdapter adapter) {
            return new IProgressBar(adapter);
        }
    }
}
