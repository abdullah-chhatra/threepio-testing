package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class HeaderMatcher(private val name: String, private val value: String?) : TypeSafeMatcher<RecordedRequest>() {

    override fun describeTo(description: Description) {
        description.appendText(" has header with name = $name")
        if (value != null) {
            description.appendText(" and value = $value")
        }
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        return if (value != null)
            request.headers.any { it == Pair(name, value) }
        else
            request.headers.any { it.first == name }
    }
}

fun RequestMatcher.hasHeader(name: String, value: String? = null) {
    add(HeaderMatcher(name, value))
}

fun RequestMatcher.hasHeader(name: String, value: Int) {
    add(HeaderMatcher(name, value.toString()))
}

fun RequestMatcher.hasHeader(name: String, value: Float) {
    add(HeaderMatcher(name, value.toString()))
}

fun RequestMatcher.hasHeader(name: String, value: Long) {
    add(HeaderMatcher(name, value.toString()))
}

fun RequestMatcher.hasHeader(name: String, value: Double) {
    add(HeaderMatcher(name, value.toString()))
}

