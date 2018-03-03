package com.threepio.testing;

import android.support.annotation.NonNull;

import com.threepio.caffelatte.interactors.ITextView;
import com.threepio.caffelatte.interactors.IView;
import com.threepio.caffelatte.interactors.IViewGroupBase;
import com.threepio.caffelatte.interactors.InteractionAdapter;
import com.threepio.caffelatte.interactors.InteractionAdapterFactory;

import static com.threepio.caffelatte.interactors.ITextView.forTextView;
import static com.threepio.caffelatte.interactors.IView.forView;

class ContactItem extends IViewGroupBase<ContactItem> {

  private ITextView name= forTextView().withId(R.id.name).withParent(this).build();

  private ITextView status = forTextView().withId(R.id.status).withParent(this).build();

  private IView online = forView().withId(R.id.online).withParent(this).build();

  private ContactItem(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  ContactItem hasName(String name) {
    this.name.isDisplayed().hasText(name);
    return this;
  }

  ContactItem hasStatus(String status) {
    this.status.isDisplayed().hasText(status);
    return this;
  }

  ContactItem isOnline() {
    online.isDisplayed();
    return this;
  }

  ContactItem isOffline() {
    online.isNotDisplayed();
    return this;
  }

  static public class Builder extends IViewGroupBase.Builder<ContactItem, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    Builder withName(String name) {
      return withChild(
        forTextView()
          .withId(R.id.name)
          .withText(name)
          .build());
    }

    Builder withStatus(String status) {
      return withChild(
        forTextView()
          .withId(R.id.status)
          .withText(status)
          .build());
    }

    Builder isOnline() {
      return withChild(
        forView()
          .withId(R.id.online)
          .isDisplayed()
          .build());
    }

    Builder isOffline() {
      return withChild(
        forView()
          .withId(R.id.online)
          .isNotDisplayed()
          .build());
    }

    @Override
    protected ContactItem create(@NonNull InteractionAdapter adapter) {
      return new ContactItem(adapter);
    }
  }
}
