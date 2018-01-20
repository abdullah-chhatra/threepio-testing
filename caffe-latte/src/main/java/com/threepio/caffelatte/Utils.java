package com.threepio.caffelatte;

import android.support.annotation.StringRes;

import static android.support.test.InstrumentationRegistry.getTargetContext;

public class Utils {

  public static String getString(@StringRes int resourceId) {
    return getTargetContext().getString(resourceId);
  }
}
