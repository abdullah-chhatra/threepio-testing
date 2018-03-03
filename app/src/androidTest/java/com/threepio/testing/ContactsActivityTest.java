package com.threepio.testing;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.threepio.caffelatte.interactors.ISingleItemTypeRecyclerView;
import com.threepio.caffelatte.interactors.ISingleItemTypeRecyclerViewBase;
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

@RunWith(AndroidJUnit4.class)
public class ContactsActivityTest {

  @Rule
  public ActivityTestRule<ContactsActivity> rule =
    new ActivityTestRule<>(ContactsActivity.class);

//  private ContactsView contactsView = forContactsView()
//    .withId(R.id.contacts_view)
//    .build();

  private ISingleItemTypeRecyclerView<ContactItem.Builder> contactsView =
    ISingleItemTypeRecyclerView.<ContactItem.Builder>forRecyclerView()
      .withId(R.id.contacts_view)
      .build();


  @Test
  public void testAtPosition() {
    ContactItem ace = contactsView.item().atPosition(0).get();

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

