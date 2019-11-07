package com.amaz.dev.android.jobsaround.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    const val REQUIRE_AUTHENTICATION = "Require-Authentication"
    private lateinit var retrofit: Retrofit
    val apiService: ApiService by lazy { retrofit.create(
        ApiService::class.java) }
    var authToken: String? = null

    fun init(baseUrl: String, isDebug: Boolean = false) {
        retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(buildClient(isDebug))
            .build()
    }

    private fun buildClient(isDebug: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(ApiInterceptor)
        if (isDebug) {
            client.addInterceptor(logging)
        }
        return client.build()
    }

    object ApiInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = if (request.header(REQUIRE_AUTHENTICATION) != null) {
                request.newBuilder()
                    .removeHeader(REQUIRE_AUTHENTICATION)
                    .addHeader("Authorization", "Bearer $authToken")
                    .addHeader("Accept", "application/json")
                    .build()
            } else {
                request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
            }
            return chain.proceed(request)
        }
    }
}
