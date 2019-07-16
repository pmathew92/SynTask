package com.prince.syntask.data.remote

import com.prince.syntask.model.ApiResponse
import retrofit2.http.GET


interface ApiService {
    @GET("bins/19u0sf")
    suspend fun getVariants(): ApiResponse
}