package com.achhatra.threepio.testing.testapp


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.achhatra.threepio.testing.caffelatte.interactors.ISingleItemTypeRecyclerView.forRecyclerView
import com.threepio.testing.ContactsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("ClassName")
@RunWith(AndroidJUnit4::class)
class ContactsActivityTest_Kotlin {

  @get:Rule
  var rule = ActivityTestRule(ContactsActivity::class.java)

  private val contactsView = forRecyclerView<ContactItem.Builder>()
    .withId(R.id.contacts_view)
    .withItemBuilder(ContactItem.Builder::class.java)
    .build()

  @Test
  fun testAtPosition() {
    val ace = contactsView.item()
      .atPosition(0)
      .get()

    ace
      .hasName("Ace Ventura")
      .hasStatus("The pet detective")
      .isOnline
  }

  @Test
  fun testItemViewMatcher() {
    val jack = contactsView.item().hasView()
      .withName("Jack Sparrow")
      .get()

    jack
      .hasStatus("About to go on a new adventure")
      .isOffline
  }
}

