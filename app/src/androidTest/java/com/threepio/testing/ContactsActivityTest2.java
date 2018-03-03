package com.threepio.testing;


import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;

import com.threepio.caffelatte.interactors.ISingleItemTypeRecyclerView;
import com.threepio.caffelatte.interactors.ISingleItemTypeRecyclerViewBase;
import com.threepio.caffelatte.interactors.InteractionAdapter;
import com.threepio.caffelatte.interactors.InteractionAdapterFactory;
import com.threepio.caffelatte.interactors.ViewInteractionAdapter;

import org.junit.Rule;
import org.junit.Test;

import static com.threepio.testing.ContactsView.forContactsView;

public class ContactsActivityTest2 {
  @Rule
  public ActivityTestRule<ContactsActivity> rule =
    new ActivityTestRule<>(ContactsActivity.class);

  private ContactsView contactsView = forContactsView()
    .withId(R.id.contacts_view)
    .build();


  @Test
  public void testAtPosition() {
    ContactItem ace = contactsView.contactAtPosition(0);

    ace
      .hasName("Ace Ventura")
      .hasStatus("The pet detective")
      .isOnline();
  }

  @Test
  public void testItemViewMatcher() {
    ContactItem jack = contactsView.contactItem()
      .withName("Jack Sparrow")
      .get();

    jack
      .hasStatus("About to go on a new adventure")
      .isOffline();
  }
}

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
