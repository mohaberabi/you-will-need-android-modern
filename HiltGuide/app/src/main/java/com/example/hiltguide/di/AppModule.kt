package com.example.hiltguide.di

import com.example.hiltguide.data.LoserApi
import com.example.hiltguide.data.LoserRepository1
import com.example.hiltguide.data.LoserRepositroy2
import com.example.hiltguide.domain.LoserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


/**
 * the app module means that
 * the instances that created inside of it we want them to live as long as the whole app is alive
 * annotated with[InstallIn(SingletonComponent::class)]
 * this means that this whole module will live as long as the whole app is alive
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideLoserApi(): LoserApi {
        return Retrofit.Builder()
            .baseUrl("https:mohab.the.best.loser.com")
            .build().create(LoserApi::class.java)
    }


    @Provides
    @Singleton
    @Named("repo1")
    fun provideLoserRepository1(api: LoserApi): LoserRepository {

        return LoserRepository1(api)
    }

    @Provides
    @Singleton
    @Named("repo1")
    fun provideLoserRepository2(api: LoserApi): LoserRepository {

        return LoserRepositroy2(api)
    }
}