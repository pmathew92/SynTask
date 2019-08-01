package com.prince.data.repository

import com.prince.data.mapper.Mapper
import com.prince.data.model.VariantsNetwork
import com.prince.data.remote.ApiService
import com.prince.domain.entities.VariantsEntity
import com.prince.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper<VariantsNetwork, VariantsEntity>
) : MainRepository {

    override suspend fun fetchResults(): VariantsEntity {
        return mapper.from(apiService.getVariants().variantsNetwork)
    }
}