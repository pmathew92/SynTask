package com.prince.syntask.ui

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prince.domain.entities.VariantsEntity
import com.prince.domain.usecases.GetVariantsUseCase
import com.prince.syntask.mapper.Mapper
import com.prince.syntask.model.Variants
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val usecase: GetVariantsUseCase,
    private val mapper: Mapper<VariantsEntity, Variants>
) :
    ViewModelProvider.NewInstanceFactory() {

    @NonNull
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //noinspection unchecked
        return MainActivityViewModel(usecase, mapper) as T
    }
}