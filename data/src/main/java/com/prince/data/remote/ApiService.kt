package com.prince.data.remote

import com.prince.data.model.ResponseWrapper
import retrofit2.http.GET


interface ApiService {
    @GET("bins/19u0sf")
    suspend fun getVariants(): ResponseWrapper
}