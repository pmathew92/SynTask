package com.prince.domain.repository

import com.prince.domain.entities.VariantsEntity

interface MainRepository {

    suspend fun fetchResults(): VariantsEntity
}