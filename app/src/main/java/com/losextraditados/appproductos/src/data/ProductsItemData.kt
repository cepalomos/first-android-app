package com.losextraditados.appproductos.src.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsItemData(
    val createdAt: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val productType: String,
    val updatedAt: String
) : Parcelable
