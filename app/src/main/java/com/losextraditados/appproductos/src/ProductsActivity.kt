package com.losextraditados.appproductos.src

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.adapter.ProductAdapter
import com.losextraditados.appproductos.src.data.ProductsItemData
import com.losextraditados.appproductos.src.reponses.ApiResProducts
import com.losextraditados.appproductos.src.service.ApiSerProduct
import com.losextraditados.appproductos.src.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    private lateinit var apiService: ApiSerProduct
    private lateinit var recicleView: RecyclerView

    companion object{
        const val PRODUCT = "PRODUCT_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        initRecicleview()
    }

    private fun initRecicleview() {
        recicleView = findViewById(R.id.products_list)

        apiService = RetrofitClient.getProducts()

        getAllProducts()
    }

    private fun getAllProducts() {
        val call: Call<ApiResProducts> = apiService.getProducts()

        call.enqueue(object : Callback<ApiResProducts> {
            override fun onResponse(
                call: Call<ApiResProducts>,
                response: Response<ApiResProducts>
            ) {
                if (response.isSuccessful) {
                    response.body()?.mapNotNull { it as? ProductsItemData }?.let { listProducts ->
                        recicleView.layoutManager = LinearLayoutManager(this@ProductsActivity)
                        recicleView.adapter =
                            ProductAdapter(listProducts) { productsItemData ->
                                productSelect(productsItemData)
                            }
                    }

                }
            }

            override fun onFailure(call: Call<ApiResProducts>, t: Throwable) {
                Toast.makeText(this@ProductsActivity, "No se que paso", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun productSelect(productsItemData: ProductsItemData) {
        val intent = Intent(this,DetailProductActivity::class.java)
        intent.putExtra(PRODUCT,productsItemData)
        startActivity(intent)
    }
}