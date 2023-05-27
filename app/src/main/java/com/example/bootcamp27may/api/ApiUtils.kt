package com.example.bootcamp27may.api

class ApiUtils {
    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
        fun getProductApi(): ProductApi {
            return RetrofitClient.getRetrofit(BASE_URL).create(ProductApi::class.java)
        }
    }
}