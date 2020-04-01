package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import java.net.URI
import java.net.URLDecoder

fun RequestMatcher.hasCsvGetParam(name: String, value: String) {
    add(GetParamCsvMatcher(GetParamCsvMatcher.Param(name, value)))
}

class GetParamCsvMatcher(private val param: Param): TypeSafeMatcher<RecordedRequest>()  {

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
        return Param(decode(list[0]), decode(list[1]).trim())
    }

    private fun decode(value: String): String {
        return URLDecoder.decode(value, "UTF-8")
    }

    class Param(val name: String, val value: String) {

        private val values = value.split(",").map { it.trim() }

        override fun equals(other: Any?): Boolean {
            return (other is Param)
                    && name == other.name
                    && values.containsAll(other.values)
                    && other.values.containsAll(values)
        }

        override fun hashCode(): Int {
            return name.hashCode() * 31 + values.hashCode()
        }
    }
}
