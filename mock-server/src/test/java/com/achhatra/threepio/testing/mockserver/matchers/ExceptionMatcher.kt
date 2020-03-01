package com.achhatra.threepio.testing.mockserver.matchers

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import retrofit2.HttpException

fun isNotFoundException(): Matcher<Throwable> {

    return object : BaseMatcher<Throwable>() {

        override fun describeTo(description: Description) {
            description.appendText("HttpException - 404 Not Found")
        }

        override fun matches(item: Any?): Boolean {
            return item is HttpException &&
                    item.code() == 404
        }
    }
}

fun isServerErrorException(): Matcher<Throwable> {
    return object : BaseMatcher<Throwable>() {
        override fun describeTo(description: Description) {
            description.appendText("HttpException - 500 Server Error")
        }

        override fun matches(item: Any?): Boolean {
            return item is HttpException &&
                    item.code() == 500
        }

    }
}