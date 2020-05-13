package com.achhatra.threepio.testing.caffelatte.matchers;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;

public class RecyclerViewItemCountMatcher extends BoundedMatcher<View, RecyclerView> {

    private int itemCount;

    public RecyclerViewItemCountMatcher(int itemCount) {
        super(RecyclerView.class);
        this.itemCount = itemCount;
    }

    @Override
    protected boolean matchesSafely(RecyclerView item) {
        RecyclerView.Adapter<?> adapter = item.getAdapter();
        if (adapter != null) {
            return adapter.getItemCount() == itemCount;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" has item count " + itemCount);
    }
}
