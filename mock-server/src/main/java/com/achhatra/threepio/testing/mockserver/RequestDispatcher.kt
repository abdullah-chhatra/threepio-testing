package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class RequestDispatcher: Dispatcher() {

    private val handlers = mutableListOf<RequestHandler>()

    fun add(handler: RequestHandler) {
        handlers.add(handler)
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        val handler = handlers.find { it.canHandle(request) }
        if(handler != null) {
            return handler.handle(request)
        }

        return MockResponse().setResponseCode(404)
    }
}