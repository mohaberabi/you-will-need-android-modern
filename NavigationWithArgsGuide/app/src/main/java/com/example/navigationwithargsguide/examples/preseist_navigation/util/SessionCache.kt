package com.example.navigationwithargsguide.examples.preseist_navigation.util

interface SessionCache {

    fun saveSession(session: Session)

    fun getActiveSession(): Session?

    fun clearSession()
}