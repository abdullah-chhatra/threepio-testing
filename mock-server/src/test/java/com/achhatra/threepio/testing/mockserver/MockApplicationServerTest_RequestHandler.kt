package com.achhatra.threepio.testing.mockserver

import com.achhatra.threepio.testing.mockserver.matchers.isServerErrorException
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class MockApplicationServerTest_RequestHandler : MockApplicationServerTestFixture() {

    @Test
    fun `get request - success`() {
        val user = User(12309L, "User Name", 24)
        server.onGetUser().returnSuccess(withUser = user)

        client.getRequest(user.id).test()
            .assertValue(user)
    }

    @Test
    fun `get request - failure`() {
        server.onGetUser().returnFailure(500)

        val exception = client.getRequest(12345).test().errors().first()

        assertThat(exception, isServerErrorException())
    }

    private fun MockApplicationServer.onGetUser(): GetUserRequestHandler {
        return addHandler(GetUserRequestHandler())
    }

    private class GetUserRequestHandler : SimpleRequestHandler() {

        override fun canHandle(request: RecordedRequest): Boolean {
            return true
        }

        fun returnSuccess(withUser: User) {
            returnSuccess(
                """
                {
                    "id": "${withUser.id}",
                    "name": "${withUser.name}",
                    "age": ${withUser.age}
                }
                """
            )
        }
    }
}