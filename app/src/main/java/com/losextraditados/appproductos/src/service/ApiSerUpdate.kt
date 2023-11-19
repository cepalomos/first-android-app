package com.losextraditados.appproductos.src.service

import com.losextraditados.appproductos.src.data.ProductsItemData
import com.losextraditados.appproductos.src.data.UpdateProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface ApiSerUpdate {

    @PUT("products")
    fun putProduct(@Body product: UpdateProduct): Call<ProductsItemData>
}