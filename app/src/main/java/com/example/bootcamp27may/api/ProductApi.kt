package com.example.bootcamp27may.api

import com.example.bootcamp27may.model.ProductResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    fun getAllProductApi(): Call<List<ProductResponseItem>>

    @GET("products/{id}")
    fun getSingleProduct(@Path("id") id: Int): Call<ProductResponseItem>

}