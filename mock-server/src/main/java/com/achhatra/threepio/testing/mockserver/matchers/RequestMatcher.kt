package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

open class RequestMatcher : TypeSafeMatcher<RecordedRequest>() {

    private val matchers = mutableListOf<Matcher<RecordedRequest>>()

    fun add(matcher: Matcher<RecordedRequest>) {
        matchers.add(matcher)
    }

    override fun describeTo(description: Description) {
        matchers.forEach {
            description
                    .appendDescriptionOf(it)
                    .appendText("\n")
        }
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        if (matchers.isEmpty()) return false

        return allOf(matchers).matches(request)
    }
}

fun request(matcher: RequestMatcher.() -> Unit): RequestMatcher {
    return RequestMatcher().apply(matcher)
}
