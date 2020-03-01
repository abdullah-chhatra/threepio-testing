package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.MockWebServer

class MockApplicationServer {

    private val internal = MockWebServer()
    private lateinit var dispatcher: RequestDispatcher

    val baseUrl = internal.url("").toString()

    fun start() {
        dispatcher = RequestDispatcher()
        internal.dispatcher = dispatcher
    }

    fun shutdown() {
        internal.shutdown()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T: RequestHandler> addHandler(handler: RequestHandler): T  {
        dispatcher.add(handler)
        return handler as T
    }
}