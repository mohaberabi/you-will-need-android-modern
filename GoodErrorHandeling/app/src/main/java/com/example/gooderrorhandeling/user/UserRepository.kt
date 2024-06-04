package com.example.gooderrorhandeling.user

import com.example.gooderrorhandeling.util.app_error.AppError
import com.example.gooderrorhandeling.util.AppResult
import com.example.gooderrorhandeling.util.app_error.DataError

interface UserRepository {


    suspend fun login(password: String): AppResult<User, DataError>


}


data class User(
    val id: String,
    val name: String,
    val email: String
)