package com.losextraditados.appproductos.src.data

data class CreateProductData(
    val name: String,
    val price: Int,
    val description: String,
    val productType : String
)