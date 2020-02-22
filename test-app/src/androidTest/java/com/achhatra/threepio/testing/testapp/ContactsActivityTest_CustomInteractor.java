package com.achhatra.threepio.testing.testapp;


import androidx.test.rule.ActivityTestRule;

import com.threepio.testing.ContactsActivity;

import org.junit.Rule;
import org.junit.Test;

import static com.achhatra.threepio.testing.testapp.ContactsView.forContactsView;

public class ContactsActivityTest_CustomInteractor {
  @Rule
  public ActivityTestRule<ContactsActivity> rule =
    new ActivityTestRule<>(ContactsActivity.class);

  private ContactsView contactsView = forContactsView()
    .withId(R.id.contacts_view)
    .withItemBuilder(ContactItem.Builder.class)
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