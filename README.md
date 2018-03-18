# Making Espresso mild: An easy way to write Espresso tests

Using Espresso is the de-facto standard of writing UI tests on Android. More often then not the test are too verbose and too difficult to read and understand. The main goal of this wrapper is enhancing readability and maintainability of your test code, and not always less code. 

Espresso test tends to be unnecessarily verbose. For example in a sign-in activity if I want assert that the user name `EditText` is displayed, has some hint text and it is empty I need to write:

```
public void testInitialUi() {
  onView(withId(R.id.user_name)).check(matches(isDisplayed()));
  onView(withId(R.id.user_name)).check(matches(withHint("User Email")));
  onView(withId(R.id.user_name)).check(matches(withText("")));
}
```

The above code does not make it easy to understand what is really being tested. Using caffe-latte the same test can performed using following code:

```
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

The library includes following wrapper for views:

1. IView 
1. IViewGroup
1. ITextView
1. IEditText
1. IButton
1. ICheckBox
1. ISwitch
1. IRadioButton
1. IToggleButton
1. IRecyclerView

