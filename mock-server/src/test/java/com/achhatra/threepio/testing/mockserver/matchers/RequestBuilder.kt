package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.Headers
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import java.net.Socket

class RequestBuilder {

    private var method = "GET"
    private var path = "/"

    fun method(method: String) = apply {
        this.method = method
    }

    fun build(): RecordedRequest {
        return RecordedRequest("$method $path HTTP1.1", Headers.headersOf(), emptyList(), 0, Buffer(), 0, Socket())
    }

    fun path(path: String) = apply {
        this.path = path
    }
}
