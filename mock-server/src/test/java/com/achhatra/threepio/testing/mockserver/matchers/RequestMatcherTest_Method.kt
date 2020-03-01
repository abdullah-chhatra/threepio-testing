package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


@Suppress("ClassName")
class RequestMatcherTest_Method {

    @Test
    fun `get method`() {
        val isGetRequest = request { isGet() }

        val request = RequestBuilder().method("GET").build()

        assertThat(request, isGetRequest)
    }

    @Test
    fun `post method`() {
        val isPostRequest = request { isPost() }

        val request = RequestBuilder().method("POST").build()

        assertThat(request, isPostRequest)
    }

    @Test
    fun `put method`() {
        val isPutRequest = request { isPut() }

        val request = RequestBuilder().method("PUT").build()

        assertThat(request, isPutRequest)
    }

    @Test
    fun `delete method`() {
        val isDeleteRequest = request { isDelete() }

        val request = RequestBuilder().method("DELETE").build()

        assertThat(request, isDeleteRequest)
    }
}
