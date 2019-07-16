package com.prince.syntask.di

import com.prince.syntask.ui.MainActivity
import com.prince.syntask.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindUserActivity(): MainActivity
}