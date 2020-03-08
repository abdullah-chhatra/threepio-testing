package com.achhatra.threepio.testing.mockserver.matchers

import okhttp3.Headers
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import java.net.Socket
import java.net.URLEncoder

class RequestBuilder {

    private var method = "GET"
    private var path = "/"
    private val headers = mutableMapOf<String, String>()
    private val formUrlParams = mutableMapOf<String, String>()

    fun method(method: String) = apply {
        this.method = method
    }

    fun path(path: String) = apply {
        this.path = path
    }

    fun header(name: String, value: String) = apply {
        headers[name] = value
    }

    fun formUrlParam(name: String, value: String) = apply {
        formUrlParams[name] = value
    }

    fun build(): RecordedRequest {
        return RecordedRequest(
                "$method $path HTTP1.1",
                headers(),
                emptyList(),
                0,
                bodyBuffer(),
                0,
                Socket())
    }

    private fun headers(): Headers {

        val extHeaders = if(formUrlParams.isNotEmpty()) {
            headers + mapOf("Content-Type" to "x-www-form-urlencoded")
        } else headers

        return Headers.of(extHeaders)
    }

    private fun bodyBuffer(): Buffer {
        if (formUrlParams.isEmpty()) return Buffer()

        val encodedString = formUrlParams
                .map { entry -> "${encode(entry.key)}=${encode(entry.value)}" }
                .joinToString("&")

        return Buffer().apply { write(encodedString.toByteArray()) }
    }

    private fun encode(value: String): String {
        return URLEncoder.encode(value, "UTF-8")
    }
}
