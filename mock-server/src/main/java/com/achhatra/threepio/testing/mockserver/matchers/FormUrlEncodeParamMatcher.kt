package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import java.net.URLDecoder

fun <T: Any> FormUrlEncodeRequestMatcher.hasParam(name: String, value: T) {
    add(FormUrlEncodeParamMatcher(FormUrlEncodeParamMatcher.Param(name, value.toString())))
}

private class FormUrlEncodeParamMatcher(val param: Param) : TypeSafeMatcher<RecordedRequest>() {

    override fun describeTo(description: Description) {
        description.appendText(" has param with name=${param.name} and value=${param.value}")
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        return request.hasParam(param)
    }

    private fun RecordedRequest.hasParam(param: Param): Boolean {
        val body = body.peek().readUtf8()
        if(body.isEmpty()) return false

        return body.splitToSequence("&")
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
