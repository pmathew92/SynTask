package com.prince.syntask.data.repository

import com.prince.syntask.data.remote.ApiService
import com.prince.syntask.model.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun fetchResults(): ApiResponse {
        return apiService.getVariants()
    }
}