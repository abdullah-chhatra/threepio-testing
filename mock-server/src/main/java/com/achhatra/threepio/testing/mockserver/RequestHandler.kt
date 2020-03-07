package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

interface RequestHandler {

    fun handle(request: RecordedRequest): MockResponse

    fun setNext(handler: RequestHandler): RequestHandler

    fun assertRequestHandled()
}