package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class PathMatcher(private val path: String): TypeSafeMatcher<RecordedRequest>() {

    override fun describeTo(description: Description) {
        description.appendText(" has path = $path")
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        return request.path == path
    }
}

class PathStartsWithMatcher(private val prefix: String): TypeSafeMatcher<RecordedRequest>() {

    override fun describeTo(description: Description) {
        description.appendText(" has path starts with $prefix")
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        return request.path!!.startsWith(prefix)
    }
}

fun RequestMatcher.hasPath(path: String) {
    add(PathMatcher(path))
}

fun RequestMatcher.hasPathThatStartsWith(path: String) {
    add(PathStartsWithMatcher(path))
}
