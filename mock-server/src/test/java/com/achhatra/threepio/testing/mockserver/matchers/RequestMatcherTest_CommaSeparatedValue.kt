package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class RequestMatcherTest_CommaSeparatedValue {

    @Test
    fun `test single param - success`() {
        val name = "1-param"
        val value = "type1, type2, type4"

        val hasParam = request {
            isGet()
            hasCsvGetParam(name, value)
        }

        val request = RequestBuilder()
                .getParam(name, "type2, type4, type1")
                .build()

        assertThat(request, hasParam)
    }

    @Test
    fun `test single param - failure`() {
        val name = "1-param"
        val value = "type1, type2, type3"

        val hasParam = request {
            isGet()
            hasCsvGetParam(name, value)
        }

        val request = RequestBuilder()
                .getParam(name, "type2, type4, type1")
                .build()

        assertThat(request, not(hasParam))
    }
}
