package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FormUrlEncodeRequestMatcherTest {

    @Test
    fun `full path - success`() {
        val path = "/request/path"
        val hasPath = formUrlEncodeRequest { hasPath(path) }

        val request = RequestBuilder()
                .method("POST")
                .path(path)
                .formUrlParam("a", "b")
                .build()

        assertThat(request, hasPath)
    }

    @Test
    fun `full path - failure`() {
        val hasPath = formUrlEncodeRequest { hasPath("/request/path") }

        val request = RequestBuilder()
                .method("POST")
                .path("/request")
                .formUrlParam("a", "b")
                .build()

        assertThat(request, not(hasPath))
    }

    @Test
    fun `test single param - success`() {
        val name = "param 1"
        val value = "value 1"

        val hasParam = formUrlEncodeRequest { hasParam(name, value) }

        val request = RequestBuilder()
                .method("POST")
                .formUrlParam(name, value)
                .build()

        assertThat(request, hasParam)
    }

    @Test
    fun `test single param - failure`() {
        val name = "param-1"
        val value = "value-1"

        val hasParam = formUrlEncodeRequest { hasParam(name, value) }

        val request = RequestBuilder()
                .method("POST")
                .formUrlParam(name, "other-value")
                .build()

        assertThat(request, not(hasParam))
    }

    @Test
    fun `test multiple params - success`() {
        val name1 = "param-1"
        val value1 = "value-1"
        val name2 = "param-2"
        val value2 = "value-2"

        val hasParam = formUrlEncodeRequest {
            hasParam(name1, value1)
            hasParam(name2, value2)
        }

        val request = RequestBuilder()
                .method("POST")
                .formUrlParam(name2, value2)
                .formUrlParam(name1, value1)
                .build()

        assertThat(request, hasParam)
    }

    @Test
    fun `single header - success`() {
        val name = "header-1"
        val value = "value-1"

        val hasHeader = formUrlEncodeRequest { hasHeader(name, value) }

        val request = RequestBuilder()
                .header(name, value)
                .method("POST")
                .formUrlParam("a", "c")
                .build()

        assertThat(request, hasHeader)
    }

    @Test
    fun `single header - no header`() {
        val name = "header-1"
        val value = "value-1"

        val hasHeader = formUrlEncodeRequest { hasHeader(name, value) }

        val request = RequestBuilder()
                .method("POST")
                .formUrlParam("a", "c")
                .build()

        assertThat(request, not(hasHeader))
    }

    @Test
    fun `single header - wrong value`() {
        val name = "header-1"
        val value = "value-1"

        val hasHeader = formUrlEncodeRequest { hasHeader(name, value) }

        val request = RequestBuilder()
                .header(name, "other-value")
                .method("POST")
                .formUrlParam("a", "c")
                .build()

        assertThat(request, not(hasHeader))
    }

    @Test
    fun `single header - no value check`() {
        val name = "header-1"
        val value = "some-value-1"

        val hasHeader = formUrlEncodeRequest { hasHeader(name) }

        val request = RequestBuilder()
                .header(name, value)
                .method("POST")
                .formUrlParam("a", "c")
                .build()

        assertThat(request, hasHeader)
    }

    @Test
    fun `multiple headers - success`() {
        val names = listOf("name-1", "name-2", "name-3")
        val values = listOf("value-1", "value-2", "value-3")

        val hasHeaders = formUrlEncodeRequest {
            hasHeader(names[0], values[0])
            hasHeader(names[1], values[1])
            hasHeader(names[2], values[2])
        }

        val request = RequestBuilder()
                .header(names[2], values[2])
                .header(names[0], values[0])
                .header(names[1], values[1])
                .method("POST")
                .formUrlParam("a", "c")
                .build()

        assertThat(request, hasHeaders)
    }
}
