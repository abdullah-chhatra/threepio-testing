package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class RequestMatcherTest_Path {

    @Test
    fun `full path - success`() {
        val path = "/request/path"
        val hasPath = request { hasPath(path) }

        val request = RequestBuilder().path(path).build()

        assertThat(request, hasPath)
    }

    @Test
    fun `full path - failure`() {
        val hasPath = request { hasPath("/request/path") }

        val request = RequestBuilder().path("/request").build()

        assertThat(request, not(hasPath))
    }

    @Test
    fun `path starts with - success`() {

        val pathStartsWith = request { hasPathThatStartsWith("/start/with") }

        val request = RequestBuilder().path("/start/with/user_id").build()

        assertThat(request, pathStartsWith)
    }

    @Test
    fun `path starts with - failure`() {

        val pathStartsWith = request { hasPathThatStartsWith("/start/something") }

        val request = RequestBuilder().path("/start/with/user_id").build()

        assertThat(request, not(pathStartsWith))
    }
}
