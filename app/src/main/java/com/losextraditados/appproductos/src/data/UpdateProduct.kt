package com.losextraditados.appproductos.src.data

data class UpdateProduct(
    val id:String,
    val name: String,
    val price: Int,
    val description: String,
    val productType : String
)
