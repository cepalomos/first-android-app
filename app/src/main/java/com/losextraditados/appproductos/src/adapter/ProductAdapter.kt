package com.losextraditados.appproductos.src.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.data.ProductsItemData

class ProductAdapter(
    private val productsList: List<ProductsItemData>,
    private val onClickListener: (ProductsItemData) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.product_view, parent, false))
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productsList[position]
        holder.render(item, onClickListener)
    }

}