package com.example.hiltguide.data

import com.example.hiltguide.domain.LoserRepository
import javax.inject.Inject

class LoserRepositroy2 @Inject constructor(private val loserApi: LoserApi) : LoserRepository {
    override suspend fun getLoserData() {
        TODO("Not yet implemented")
    }
}