package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class RequestDispatcher : Dispatcher() {

    private var handler: RequestHandler = FailingRequestHandler()

    fun add(newHandler: RequestHandler) {
        handler = newHandler.setNext(handler)
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        return handler.handle(request)
    }

    fun assertAllRequestsHandled() {
        handler.assertRequestHandled()
    }
}