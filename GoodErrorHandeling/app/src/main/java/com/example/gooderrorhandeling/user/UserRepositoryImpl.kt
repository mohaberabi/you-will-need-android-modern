package com.example.gooderrorhandeling.user

import com.example.gooderrorhandeling.util.AppResult
import com.example.gooderrorhandeling.util.app_error.DataError
import java.io.IOException


class UserRepositoryImpl : UserRepository {
    override suspend fun login(
        password: String
    ): AppResult<User, DataError> {


        return try {

            if (password.isEmpty()) {
                AppResult.Error(DataError.Local.DISK_FULL)

            } else {
                AppResult.Done(User("sadsad", "asdasd", "asdsad"))
            }
        } catch (e: IOException) {
            AppResult.Error(DataError.NetWork.REQUEST_TIMED_OUT)
        }
    }


}