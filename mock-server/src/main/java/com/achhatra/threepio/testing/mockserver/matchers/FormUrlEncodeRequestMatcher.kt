package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description

fun formUrlEncodeRequest(matcher: FormUrlEncodeRequestMatcher.() -> Unit): FormUrlEncodeRequestMatcher {
    return FormUrlEncodeRequestMatcher().apply(matcher)
}

class FormUrlEncodeRequestMatcher : RequestMatcher() {

    override fun describeTo(description: Description) {
        description.appendText(" is form url encoded request\n")
        super.describeTo(description)
    }

    override fun matchesSafely(request: RecordedRequest): Boolean {
        if(!request.isFormUrlRequest()) return false

        return super.matchesSafely(request)
    }

    private fun RecordedRequest.isFormUrlRequest(): Boolean {
        return method == "POST" &&
                headers.get("Content-Type") == "x-www-form-urlencoded"
    }
}
