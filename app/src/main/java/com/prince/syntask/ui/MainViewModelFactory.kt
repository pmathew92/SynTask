package com.prince.syntask.ui

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prince.syntask.data.repository.MainRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val mainRepository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @NonNull
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //noinspection unchecked
        return MainActivityViewModel(mainRepository) as T
    }
}