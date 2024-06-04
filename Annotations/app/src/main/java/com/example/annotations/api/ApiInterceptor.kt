package com.example.annotations.api

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())


        val shouldAttachHeader = invocation
            .method()
            .annotations
            .any {
                it.annotationClass == Authed::class
            }

        return if (shouldAttachHeader) {
            chain.proceed(chain.request().newBuilder().addHeader("Authorization", "token").build())
        } else {
            chain.proceed(chain.request())
        }
    }

}