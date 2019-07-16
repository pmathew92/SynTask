package com.prince.syntask.ui

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideUserAdapter(context: MainActivity): ItemAdapter {
        return ItemAdapter(context)
    }
}