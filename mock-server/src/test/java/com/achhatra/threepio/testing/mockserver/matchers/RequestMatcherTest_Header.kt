package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class RequestMatcherTest_Header {

    @Test
    fun `single header - success`() {
        val name = "header-1"
        val value = "value-1"

        val hasHeader = request { hasHeader(name, value) }

        val request = RequestBuilder().header(name, value).build()

        assertThat(request, hasHeader)
    }

    @Test
    fun `single header - no header`() {
        val name = "header-1"
        val value = "value-1"

        val hasHeader = request { hasHeader(name, value) }

        val request = RequestBuilder().build()

        assertThat(request, not(hasHeader))
    }

    @Test
    fun `single header - wrong value`() {
        val name = "header-1"
        val value = "value-1"

        val hasHeader = request { hasHeader(name, value) }

        val request = RequestBuilder().header(name, "other-value").build()

        assertThat(request, not(hasHeader))
    }

    @Test
    fun `single header - no value check`() {
        val name = "header-1"
        val value = "some-value-1"

        val hasHeader = request { hasHeader(name) }

        val request = RequestBuilder().header(name, value).build()

        assertThat(request, hasHeader)
    }

    @Test
    fun `multiple headers - success`() {
        val names = listOf("name-1", "name-2", "name-3")
        val values = listOf("value-1", "value-2", "value-3")

        val hasHeaders = request {
            hasHeader(names[0], values[0])
            hasHeader(names[1], values[1])
            hasHeader(names[2], values[2])
        }

        val request = RequestBuilder()
                .header(names[2], values[2])
                .header(names[0], values[0])
                .header(names[1], values[1])
                .build()

        assertThat(request, hasHeaders)
    }
}