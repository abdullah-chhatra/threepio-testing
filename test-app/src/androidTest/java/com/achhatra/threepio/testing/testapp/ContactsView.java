package com.achhatra.threepio.testing.testapp;

import androidx.annotation.NonNull;

import com.achhatra.threepio.testing.caffelatte.interactors.ISingleItemTypeRecyclerViewBase;
import com.achhatra.threepio.testing.caffelatte.interactors.InteractionAdapter;
import com.achhatra.threepio.testing.caffelatte.interactors.InteractionAdapterFactory;
import com.achhatra.threepio.testing.caffelatte.interactors.ViewInteractionAdapter;

class ContactsView extends ISingleItemTypeRecyclerViewBase<ContactItem.Builder, ContactsView> {

  private ContactsView(Class<ContactItem.Builder> itemBuilder, InteractionAdapter interactionAdapter) {
    super(itemBuilder, interactionAdapter);
  }

  ItemBuilder<ContactItem.Builder> contact() {
    return item();
  }

  ContactItem contactAtPosition(int position) {
    return item().atPosition(position).get();
  }

  ContactItem.Builder contactItem() {
    return item(ContactItem.Builder.class).hasView();
  }

  static Builder forContactsView() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  static class Builder extends ISingleItemTypeRecyclerViewBase.Builder<ContactItem.Builder, ContactsView, Builder> {

    Builder(InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected ContactsView create(@NonNull Class<ContactItem.Builder> itemBuilder, @NonNull InteractionAdapter adapter) {
      return new ContactsView(itemBuilder, adapter);
    }
  }
}
