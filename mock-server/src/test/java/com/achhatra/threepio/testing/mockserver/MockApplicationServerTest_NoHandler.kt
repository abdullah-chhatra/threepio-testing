package com.achhatra.threepio.testing.mockserver

import com.achhatra.threepio.testing.mockserver.matchers.isNotFoundException
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test

class MockApplicationServerTest_NoHandler: MockApplicationServerTestFixture() {

    @Test
    fun `given no handler registered for a request, should return 404(Not Found)`() {

        val exception = client.getRequest(2345).test().errors().first()

        assertThat(exception, isNotFoundException())
    }

//    @Test
//    fun `given no handler registered for a request, when calling shutdown should fail`() {
//
//        client.getRequest().test()
//
//        try {
//            server.shutdown()
//            fail("Should throw AssertionError")
//        } catch (e: AssertionError) {
//            assertThat(e.message, startsWith("No handler for request:"))
//        }
//    }

    @After
    fun tearDown() {
        server.shutdown()
    }


}
