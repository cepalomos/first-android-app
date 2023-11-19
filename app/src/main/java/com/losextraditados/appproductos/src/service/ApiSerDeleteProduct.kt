package com.losextraditados.appproductos.src.service

import com.losextraditados.appproductos.src.reponses.ApiResLogin
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ApiSerDeleteProduct {

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id:String):Call<ApiResLogin>

}