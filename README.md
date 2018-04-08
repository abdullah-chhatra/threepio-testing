# Making Espresso mild: An easy way to write Espresso tests

Espresso is great tool for writing UI tests on Android. But more often then not the test are too verbose and too difficult to read and understand. To overcome this limitation I wrote this wrapper library for Espresso. The main goal of this wrapper is enhancing readability and maintainability of test code, and not always less code. 

Espresso test tends to be unnecessarily verbose. For example in a sign-in activity if I want assert that the user name `EditText` is displayed, has some hint text and it is empty I need to write:

``` Java
public void testInitialUi() {
  onView(withId(R.id.user_name)).check(matches(isDisplayed()));
  onView(withId(R.id.user_name)).check(matches(withHint("User Email")));
  onView(withId(R.id.user_name)).check(matches(withText("")));
}
```

The above code does not make it easy to understand what is really being tested, there is too much repetation. Using caffe-latte the same test can performed using following code:

``` Java
private IEditText userName = IEditText.forEditText()
  .withId(R.id.user_name)
  .build();

public void testInitialUi() {
  userName
    .isDisplayed()
    .hasHint("User Email")
    .hasEmptyText()
}
```

See [this](/test-app/src/androidTest/java/com/achhatra/threepio/testing/testapp/SignInActivityTest_Java.java) activity test for more.

The library includes following wrapper for views:

1. `IView` 
1. `IViewGroup`
1. `ITextView`
1. `IEditText`
1. `IButton`
1. `ICheckBox`
1. `ISwitch`
1. `IRadioButton`
1. `IToggleButton`
1. `IRecyclerView`

## Testing compound views

Testing compound views individually may not be that big an issue, but testing their usage within an activity posses some challange. Even worse if you have multiple instance of the compound view in an activity. You will have to deal with nested matchers that will try to match view with in views. This soon becomes quite a pain and prone to mistakes. 

One techinque to solve this issue is to create your own class to encapsulate the testing behavior of the compound view. For example if you have contacts view that contains an `TextView` that displays user name, another `TextView` that displays status and a view that shows that the user is online. The interactor class can be written as:

``` Java
class ContactItem extends IViewGroupBase<ContactItem> {

  // Create interactor for each of the sub-views for this compound view.
  private ITextView name= forTextView()
    .withId(R.id.name)
    .withParent(this)   // Add this as its parent.
    .build();

  private ITextView status = forTextView()
    .withId(R.id.status)
    .withParent(this)
    .build();

  private IView online = forView()
    .withId(R.id.online)
    .withParent(this)
    .build();

  private ContactItem(@NonNull InteractionAdapter interactionAdapter) {
    super(interactionAdapter);
  }

  // You can write meaningful assertion methods for your view.
  
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

  // You need to write a builder to instantiate this class.
  
  static public class Builder extends IViewGroupBase.Builder<ContactItem, Builder> {

    public Builder(@NonNull InteractionAdapterFactory adapterFactory) {
      super(adapterFactory);
    }

    // Write meaningful expectation methods.
    
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

```

Derive your class from `IViewGroupBase<>` passing your class as generic parameter to it. The `Builder` is as important as it will allow you to instantiate the class with ease and in readable way.

``` Java

private ContactItem item = forContactItem()
      .withName("Bob")
      .build();
      

public void testMethod() {
    ace
      .hasStatus("Going to movie")
      .isOnline();
}
```
