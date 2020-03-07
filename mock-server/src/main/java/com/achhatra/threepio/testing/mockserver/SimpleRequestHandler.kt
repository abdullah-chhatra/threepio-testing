package com.achhatra.threepio.testing.mockserver

import com.achhatra.threepio.testing.mockserver.matchers.RequestMatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.StringDescription
import org.junit.Assert.fail

abstract class SimpleRequestHandler : RequestHandler {

    protected var response: MockResponse? = null
    private lateinit var nextHandler: RequestHandler
    private var requestHandled = false

    override fun handle(request: RecordedRequest): MockResponse {
        return if (matcher().matches(request)) {
            checkNotNull(response) { "No response setup for request $request" }
            requestHandled = true
            response!!
        } else {
            nextHandler.handle(request)
        }
    }

    override fun setNext(handler: RequestHandler): RequestHandler {
        nextHandler = handler
        return this
    }

    override fun assertRequestHandled() {
        if (!requestHandled) {
            val description = StringDescription()
            description.appendText("No request received that: \n")
            matcher().describeTo(description)
            fail(description.toString())
        }
        nextHandler.assertRequestHandled()
    }

    fun returnSuccess(body: String = "") {
        response = MockResponse()
                .setResponseCode(200)
                .setBody(body)
    }

    fun returnFailure(code: Int, body: String = "") {
        response = MockResponse()
                .setResponseCode(code)
                .setBody(body)
    }

    protected abstract fun matcher(): RequestMatcher
}