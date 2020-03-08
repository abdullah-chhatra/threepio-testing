package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class RequestMatcherTest_GetParam {

    @Test
    fun `test single param - success`() {
        val name = "1-param"
        val value = "1-value"

        val hasParam = request {
            isGet()
            hasGetParam(name, value)
        }

        val request = RequestBuilder()
                .getParam(name, value)
                .build()

        assertThat(request, hasParam)
    }

    @Test
    fun `test single param - failure`() {
        val name = "1-param"
        val value = "1-value"

        val hasParam = request { hasGetParam(name, value) }

        val request = RequestBuilder()
                .method("GET")
                .build()

        assertThat(request, CoreMatchers.not(hasParam))
    }

    @Test
    fun `test multiple params - success`() {
        val name1 = "1-param"
        val value1 = "1-value"
        val name2 = "2-param"
        val value2 = "2-value"

        val hasParam = request {
            hasGetParam(name1, value1)
            hasGetParam(name2, value2)
        }

        val request = RequestBuilder()
                .method("GET")
                .getParam(name2, value2)
                .getParam(name1, value1)
                .build()

        assertThat(request, hasParam)
    }
}
