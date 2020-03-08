package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class RequestMatcherTest_FormUrlParam {

    @Test
    fun `test form url request - success`() {
        val isFormRequest = request { isFormUrlEncodedRequest() }

        val request = RequestBuilder().formUrlEncoded().build()

        assertThat(request, isFormRequest)
    }

    @Test
    fun `test single param - success`() {
        val name = "param-1"
        val value = "value-1"

        val hasParam = request {
            isFormUrlEncodedRequest()
            hasFormParam(name, value)
        }

        val request = RequestBuilder()
                .formUrlEncoded()
                .formParam(name, value)
                .build()

        assertThat(request, hasParam)
    }

    @Test
    fun `test single param - failure`() {
        val name = "param-1"
        val value = "value-1"

        val hasParam = request { hasFormParam(name, value) }

        val request = RequestBuilder()
                .formUrlEncoded()
                .build()

        assertThat(request, not(hasParam))
    }

    @Test
    fun `test multiple params - success`() {
        val name1 = "param-1"
        val value1 = "value-1"
        val name2 = "param-2"
        val value2 = "value-2"

        val hasParam = request {
            hasFormParam(name1, value1)
            hasFormParam(name2, value2)
        }

        val request = RequestBuilder()
                .formUrlEncoded()
                .formParam(name2, value2)
                .formParam(name1, value1)
                .build()

        assertThat(request, hasParam)
    }
}