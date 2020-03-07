package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class FailingRequestHandler : RequestHandler {

    override fun handle(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(404)
    }

    override fun setNext(handler: RequestHandler): RequestHandler {
        throw NotImplementedError("There must not be any next handler after this.")
    }

    override fun assertRequestHandled() {}
}