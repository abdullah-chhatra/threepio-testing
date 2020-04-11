package com.achhatra.threepio.testing.mockserver

import com.achhatra.threepio.testing.mockserver.matchers.RequestMatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.StringDescription
import org.junit.Assert.fail
import java.util.*

abstract class SimpleRequestHandler : RequestHandler {

    private val responseQueue: Queue<MockResponse> = LinkedList()

    private lateinit var nextHandler: RequestHandler

    override fun handle(request: RecordedRequest): MockResponse {
        return if (matcher().matches(request)) {
            check(!responseQueue.isEmpty()) { "No response setup for request $request" }
            responseQueue.poll()
        } else {
            nextHandler.handle(request)
        }
    }

    override fun setNext(handler: RequestHandler): RequestHandler {
        nextHandler = handler
        return this
    }

    override fun assertRequestHandled() {
        if (!responseQueue.isEmpty()) {
            val description = StringDescription()
            description.appendText("No request received that: \n")
            matcher().describeTo(description)
            fail(description.toString())
        }
        nextHandler.assertRequestHandled()
    }

    fun returnSuccess(code: Int = 200, body: String = "") {
        val response = MockResponse()
                .setResponseCode(code)
                .setBody(body)
        responseQueue.offer(response)
    }

    fun returnFailure(code: Int, body: String = "") {
        val response = MockResponse()
                .setResponseCode(code)
                .setBody(body)
        responseQueue.offer(response)
    }

    protected abstract fun matcher(): RequestMatcher
}