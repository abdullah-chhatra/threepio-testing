package com.achhatra.threepio.testing.testapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.achhatra.threepio.testing.caffelatte.interactors.ISingleItemTypeRecyclerView;
import com.threepio.testing.ContactsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ContactsActivityTest_Java {

  @Rule
  public ActivityTestRule<ContactsActivity> rule =
    new ActivityTestRule<>(ContactsActivity.class);

  private ISingleItemTypeRecyclerView<ContactItem.Builder> contactsView =
    ISingleItemTypeRecyclerView.<ContactItem.Builder>forRecyclerView()
      .withId(R.id.contacts_view)
      .withItemBuilder(ContactItem.Builder.class)
      .build();

  @Test
  public void testAtPosition() {
    ContactItem ace = contactsView.item()
      .atPosition(0)
      .get();

    ace
      .hasName("Ace Ventura")
      .hasStatus("The pet detective")
      .isOnline();
  }

  @Test
  public void testItemViewMatcher() {
    ContactItem jack = contactsView.item().hasView()
      .withName("Jack Sparrow")
      .get();

    jack
      .hasStatus("About to go on a new adventure")
      .isOffline();
  }
}

