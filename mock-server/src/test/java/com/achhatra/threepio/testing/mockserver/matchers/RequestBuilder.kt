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
    private val getParams = mutableMapOf<String, String>()

    fun method(method: String) = apply {
        this.method = method
    }

    fun formUrlEncoded() = apply {
        method("POST")
        header("Content-Type", "application/x-www-form-urlencoded")
    }

    fun path(path: String) = apply {
        this.path = path
    }

    fun getParam(name: String, value: String) = apply {
        getParams[name] = value
    }

    fun header(name: String, value: String) = apply {
        headers[name] = value
    }

    fun formParam(name: String, value: String) = apply {
        formUrlParams[name] = value
    }

    fun build(): RecordedRequest {
        return RecordedRequest(
                "$method ${path()} HTTP1.1",
                headers(),
                emptyList(),
                0,
                bodyBuffer(),
                0,
                Socket())
    }

    private fun path(): String {
        if(getParams.isEmpty()) return path

        return "$path?" + getParams
                .map { "${encode(it.key)}=${encode(it.value)}" }
                .joinToString("&")
    }

    private fun headers(): Headers {
        return Headers.of(headers)
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
