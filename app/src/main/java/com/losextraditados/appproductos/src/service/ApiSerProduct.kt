package com.losextraditados.appproductos.src.service

import com.losextraditados.appproductos.src.reponses.ApiResProducts
import retrofit2.Call
import retrofit2.http.GET

interface ApiSerProduct {
    @GET("products")
    fun getProducts():Call<ApiResProducts>
}