package com.achhatra.threepio.testing.caffelatte.actions;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static org.hamcrest.Matchers.allOf;

public class ScrollToEndAction extends TypeSafeViewAction<RecyclerView> {

    public ScrollToEndAction() {
        super(RecyclerView.class);
    }

    @Override
    public String getDescription() {
        return "Scrolls to the end of recycler view items";
    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(super.getConstraints(), isDisplayingAtLeast(90));
    }

    @Override
    protected void performSafely(UiController uiController, RecyclerView view) {
        RecyclerView.Adapter<?> adapter = view.getAdapter();
        if(adapter != null) {
            int lastItemIndex = adapter.getItemCount() - 1;
            view.scrollToPosition(lastItemIndex);
        }
        uiController.loopMainThreadUntilIdle();
    }
}
