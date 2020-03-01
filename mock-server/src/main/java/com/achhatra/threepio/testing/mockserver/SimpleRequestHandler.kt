package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

abstract class SimpleRequestHandler : RequestHandler {

    protected var response: MockResponse? = null

    override fun handle(request: RecordedRequest): MockResponse {
        checkNotNull(response) { "No response setup for request $request" }
        return response!!
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
}