package com.achhatra.threepio.testing.mockserver.matchers

import com.achhatra.threepio.testing.mockserver.matchers.GetParamMatcher.*
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import java.net.URI
import java.net.URLDecoder

fun <T: Any> RequestMatcher.hasGetParam(name: String, value: T) {
    add(GetParamMatcher(Param(name, value.toString())))
}

private class GetParamMatcher(private val param: Param): TypeSafeMatcher<RecordedRequest>() {

    override fun describeTo(description: Description) {
        description.appendText("has GET param with name=${param.name} and value=${param.value}")
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        return request.hasParam(param)
    }

    private fun RecordedRequest.hasParam(param: Param): Boolean {
        val query = URI.create(path).query ?: return false
        return query.splitToSequence("&")
                .map { it.toParam() }
                .contains(param)
    }

    private fun String.toParam(): Param {
        val list = split("=")
        return Param(decode(list[0]), decode(list[1]))
    }

    private fun decode(value: String): String {
        return URLDecoder.decode(value, "UTF-8")
    }
    data class Param(val name: String, val value: String)
}
