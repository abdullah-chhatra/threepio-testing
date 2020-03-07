package com.achhatra.threepio.testing.mockserver

import io.reactivex.Single
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

abstract class MockApplicationServerTestFixture {

    protected lateinit var server: MockApplicationServer
    protected lateinit var client: Client

    @Before
    fun setup() {
        server = MockApplicationServer()
        server.start()
        client = createClient()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun createClient(): Client {
        return Retrofit.Builder()
            .baseUrl(server.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .callTimeout(100, TimeUnit.MILLISECONDS)
                    .build())
            .build()
            .create(Client::class.java)
    }

    interface Client {
        @GET("/user/{id}")
        fun getRequest(@Path("id") id: Long): Single<User>
    }

    data class User(val id: Long, val name: String, val age: Int)
}