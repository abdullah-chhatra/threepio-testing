package com.achhatra.threepio.testing.mockserver

import com.achhatra.threepio.testing.mockserver.matchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@Suppress("ClassName")
class MockApplicationServerTest_RequestHandler : MockApplicationServerTestFixture() {

    @Test
    fun `request success`() {
        val user = User(12309L, "User Name", 24)
        server.onGetUser(withId = user.id).returnSuccess(withUser = user)

        client.getRequest(user.id).test()
                .assertValue(user)
    }

    @Test
    fun `request - failure`() {
        server.onGetUser(12345).returnFailure(code = 500)

        val exception = client.getRequest(12345).test().errors().first()

        assertThat(exception, isServerErrorException())
    }

    private fun MockApplicationServer.onGetUser(withId: Long): GetUserRequestHandler {
        return addHandler(GetUserRequestHandler(withId))
    }

    private class GetUserRequestHandler(val userId: Long) : SimpleRequestHandler() {

        override fun matcher(): RequestMatcher {
            return request {
                isGet()
                hasPath("/user/$userId")
            }
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