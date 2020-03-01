package com.achhatra.threepio.testing.mockserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

interface RequestHandler {

    fun canHandle(request: RecordedRequest): Boolean

    fun handle(request: RecordedRequest): MockResponse
}