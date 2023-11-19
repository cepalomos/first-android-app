package com.losextraditados.appproductos.src.service

import com.losextraditados.appproductos.src.data.CreateProductData
import com.losextraditados.appproductos.src.data.ProductsItemData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiSerCreate {

    @POST("products")
    fun postProduct(@Body product: CreateProductData): Call<ProductsItemData>
}