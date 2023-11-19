package com.losextraditados.appproductos.src.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.data.ProductsItemData

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nameProduct = view.findViewById<TextView>(R.id.tvName)
    val typeProduct = view.findViewById<TextView>(R.id.tvTypeProduct)
    val priceProduct = view.findViewById<TextView>(R.id.tvPrice)
    val descriptionProduct = view.findViewById<TextView>(R.id.tvDescription)
    val imageProduct = view.findViewById<ImageView>(R.id.ivProduct)

    fun render(productModel: ProductsItemData, onClickListener: (ProductsItemData) -> Unit) {
        nameProduct.text = productModel.name
        typeProduct.text = productModel.productType
        priceProduct.text = "Precio: ${productModel.price.toString()}"
        descriptionProduct.text = productModel.description
        Glide.with(imageProduct.context).load(productModel.imageUrl).into(imageProduct)

        itemView.setOnClickListener { onClickListener(productModel) }
    }
}