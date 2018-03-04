package com.achhatra.threepio.testing.testapp;

import android.support.annotation.NonNull;

import com.achhatra.threepio.testing.caffelatte.interactors.ITextView;
import com.achhatra.threepio.testing.caffelatte.interactors.IView;
import com.achhatra.threepio.testing.caffelatte.interactors.IViewGroupBase;
import com.achhatra.threepio.testing.caffelatte.interactors.InteractionAdapter;
import com.achhatra.threepio.testing.caffelatte.interactors.InteractionAdapterFactory;

import static com.achhatra.threepio.testing.caffelatte.interactors.ITextView.forTextView;
import static com.achhatra.threepio.testing.caffelatte.interactors.IView.forView;

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
