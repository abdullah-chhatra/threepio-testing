package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class MethodMatcher(private val method: String): TypeSafeMatcher<RecordedRequest>() {

    override fun describeTo(description: Description) {
        description.appendText(" has method = $method")
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        return request.method == method
    }
}

fun RequestMatcher.isGet() {
    add(MethodMatcher("GET"))
}

fun RequestMatcher.isPost() {
    add(MethodMatcher("POST"))
}

fun RequestMatcher.isPut() {
    add(MethodMatcher("PUT"))
}

fun RequestMatcher.isDelete() {
    add(MethodMatcher("DELETE"))
}
