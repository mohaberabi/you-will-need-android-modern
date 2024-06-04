package com.example.annotations.api

interface MyApi {


    @Authed
    suspend fun getData()
}


@Target(AnnotationTarget.FUNCTION)
annotation class Authed()