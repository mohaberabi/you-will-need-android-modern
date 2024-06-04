package com.example.hiltguide.di

import com.example.hiltguide.data.LoserRepository1
import com.example.hiltguide.data.LoserRepositroy2
import com.example.hiltguide.domain.LoserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


/**
 * we use [Binds] when we need some instance to be live at a certain scope
 * in this example we need the whole app's repository to be live only as long as they needed
 * eg: you created the instance of the viewmodel that has an instance of some repository in the constructor
 * when viewmodel is cleared that means that the instances of the repository that are annotated with [Binds]
 * that the viewmodel has an instance for them will also be cleared and if the view model is became alive then after
 * the repositories also will be created with a new isntance not the old instance as they are not a singeltons
 * they are lazy instances that are disposed when not needed again even if the app is still alive
 * [NOTE] you will need to inject the instances also as they need dependencies also
 */

@Module

@InstallIn(SingletonComponent::class)
abstract class RepoistoryModule {


    @Binds
    @Singleton
    @Named("repo1")
    abstract fun bindRepository1(
        repositroyRepository1:
        LoserRepository1
    ): LoserRepository

    @Binds
    @Singleton
    @Named("repo2")
    abstract fun bindRepository2(
        repositroyRepository2:
        LoserRepositroy2
    ): LoserRepository


}