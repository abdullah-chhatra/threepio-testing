package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.Headers
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import java.net.Socket

class RequestBuilder {

    private var method = "GET"
    private var path = "/"
    private val headers = mutableMapOf<String, String>()

    fun method(method: String) = apply {
        this.method = method
    }

    fun path(path: String) = apply {
        this.path = path
    }

    fun header(name: String, value: String) = apply {
        headers[name] = value
    }

    fun build(): RecordedRequest {
        return RecordedRequest(
                requestLine = "$method $path HTTP1.1",
                headers = headers(),
                chunkSizes = emptyList(),
                bodySize = 0,
                body = Buffer(),
                sequenceNumber = 0,
                socket = Socket())
    }

    private fun headers(): Headers {
        val nameValues = headers
                .flatMap { k -> listOf(k.key, k.value) }
                .toTypedArray()

        return Headers.headersOf(*nameValues)
    }
}
