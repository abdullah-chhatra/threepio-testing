package com.threepio.testing;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.threepio.caffelatte.interactors.IRecyclerViewBase;
import com.threepio.caffelatte.interactors.ITextView;
import com.threepio.caffelatte.interactors.IView;
import com.threepio.caffelatte.interactors.IViewGroupBase;
import com.threepio.caffelatte.interactors.InteractionAdapter;
import com.threepio.caffelatte.interactors.InteractionAdapterFactory;
import com.threepio.caffelatte.interactors.ViewInteractionAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.threepio.caffelatte.interactors.ITextView.forTextView;
import static com.threepio.caffelatte.interactors.IView.forView;
import static com.threepio.testing.ContactsView.forContactsView;

@RunWith(AndroidJUnit4.class)
public class ContactsActivityTest {

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

class ContactsView extends IRecyclerViewBase<ContactsView> {

  private ContactsView(InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  ContactItem contactAtPosition(int position) {
    return item(ContactItem.Builder.class).atPosition(position).get();
  }

  ContactItem.Builder contactItem() {
    return item(ContactItem.Builder.class).hasView();
  }

  static Builder forContactsView() {
    return new Builder(new ViewInteractionAdapter.Factory());
  }

  static class Builder extends IRecyclerViewBase.Builder<ContactsView, Builder> {

    public Builder(InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    @Override
    protected ContactsView create(@NonNull InteractionAdapter adapter) {
      return new ContactsView(adapter);
    }
  }
}

class ContactItem extends IViewGroupBase<ContactItem> {

  ITextView name= forTextView().withId(R.id.name).withParent(this).build();

  ITextView status = forTextView().withId(R.id.status).withParent(this).build();

  IView online = forView().withId(R.id.online).withParent(this).build();

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
