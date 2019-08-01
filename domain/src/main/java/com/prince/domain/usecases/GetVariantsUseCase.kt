package com.prince.domain.usecases

import com.prince.domain.entities.VariantsEntity
import com.prince.domain.repository.MainRepository
import com.prince.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class GetVariantsUseCase @Inject constructor(private var repository: MainRepository) : BaseUseCase<VariantsEntity>() {
    override suspend fun execute(): VariantsEntity {
        return repository.fetchResults()
    }

}