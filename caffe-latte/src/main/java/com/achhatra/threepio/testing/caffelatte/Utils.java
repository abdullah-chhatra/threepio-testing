package com.achhatra.threepio.testing.caffelatte;

import androidx.annotation.StringRes;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;


public class Utils {

  public static String getString(@StringRes int resourceId) {
    return getApplicationContext().getString(resourceId);
  }
}
