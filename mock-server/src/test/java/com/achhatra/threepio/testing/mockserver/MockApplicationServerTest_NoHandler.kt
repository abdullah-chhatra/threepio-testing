package com.achhatra.threepio.testing.mockserver

import com.achhatra.threepio.testing.mockserver.matchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MockApplicationServerTest_NoHandler : MockApplicationServerTestFixture() {

    @Test
    fun `given no handler registered for a request, should return 404(Not Found)`() {

        val exception = client.getRequest(2345).test().errors().first()

        assertThat(exception, isNotFoundException())
    }

    @Test(expected = AssertionError::class)
    fun `no requests received for given handlers`() {
        val server = MockApplicationServer()
        server.start()

        server.addHandler<RequestHandler>(requestHandler("/path1"))
        server.addHandler<RequestHandler>(requestHandler("/path2"))

        client.getRequest(10).test()

        server.shutdown()
    }

    private fun requestHandler(path: String): RequestHandler {
        return object : SimpleRequestHandler() {

            init { returnSuccess() }

            override fun matcher(): RequestMatcher {
                return request {
                    isPost()
                    hasPath(path)
                }
            }
        }
    }
}
