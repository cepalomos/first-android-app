package com.losextraditados.appproductos.src

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.ProductsActivity.Companion.PRODUCT
import com.losextraditados.appproductos.src.data.ProductsItemData
import com.losextraditados.appproductos.src.reponses.ApiResLogin
import com.losextraditados.appproductos.src.service.RetrofitClient
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductActivity : AppCompatActivity() {

    private lateinit var nameProdutc: MaterialTextView
    private lateinit var descriptionProdutc: MaterialTextView
    private lateinit var priceProdutc: MaterialTextView
    private lateinit var typeProdutc: MaterialTextView
    private lateinit var ivProduct: ImageView
    private lateinit var btnEliminar: MaterialButton
    private lateinit var btnEditar: MaterialButton
    private lateinit var btnBack: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        val productData: ProductsItemData = intent.getParcelableExtra(PRODUCT) ?: ProductsItemData(
            createdAt = "Fecha ficticia",
            description = "Descripción ficticia",
            id = "ID ficticio",
            imageUrl = "https://www.shutterstock.com/shutterstock/photos/2073816113/display_1500/stock-vector-corrupted-file-concept-illustration-flat-design-vector-eps-modern-graphic-element-for-landing-2073816113.jpg",
            name = "Nombre ficticio",
            price = 0,
            productType = "Tipo de producto ficticio",
            updatedAt = "Fecha de actualización ficticia"
        )

        initComponents()
        initListeners(productData)
        initUI(productData)
    }

    private fun initUI(productData: ProductsItemData) {
        nameProdutc.text = productData.name
        descriptionProdutc.text = productData.description
        typeProdutc.text = productData.productType
        priceProdutc.text = productData.price.toString()
        Glide.with(ivProduct.context).load(productData.imageUrl).into(ivProduct)
    }

    private fun initListeners(productData: ProductsItemData) {
        btnEliminar.setOnClickListener {
            deleteProductApi(productData.id)
        }
        btnBack.setOnClickListener { this@DetailProductActivity.onBackPressedDispatcher.onBackPressed() }
        btnEditar.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(PRODUCT, productData)
            startActivity(intent)
        }
    }

    private fun deleteProductApi(id: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar producto")
        builder.setMessage("¿Deseas eliminar este producto?")

// Configura el botón positivo
        builder.setPositiveButton("Sí") { dialog, which ->
            // Aquí puedes poner el código que se ejecutará cuando el usuario haga clic en "Sí"
            deleteApi(id)
        }

// Configura el botón negativo
        builder.setNegativeButton("No") { dialog, which ->
            // Aquí puedes poner el código que se ejecutará cuando el usuario haga clic en "No"
            Toast.makeText(applicationContext, "Operación cancelada", Toast.LENGTH_SHORT).show()
        }

// Muestra el diálogo
        builder.show()

    }

    private fun deleteApi(id: String) {
        val apiService = RetrofitClient.deleteProducts()
        val call: Call<ApiResLogin> = apiService.deleteProduct(id)
        call.enqueue(object : Callback<ApiResLogin> {
            override fun onResponse(call: Call<ApiResLogin>, response: Response<ApiResLogin>) {

                if (response.isSuccessful && response.body()?.message == "Eliminado correctamente"){
                    Toast.makeText(
                        this@DetailProductActivity,
                        response.body()?.message ?: "No he llegado",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@DetailProductActivity,ProductsActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ApiResLogin>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al eliminar", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun initComponents() {
        nameProdutc = findViewById(R.id.tvName)
        descriptionProdutc = findViewById(R.id.tvDescription)
        priceProdutc = findViewById(R.id.tvPrice)
        typeProdutc = findViewById(R.id.tvTypeProduct)
        ivProduct = findViewById(R.id.ivProduct)
        btnBack = findViewById(R.id.btnSalir)
        btnEditar = findViewById(R.id.btnEditar)
        btnEliminar = findViewById(R.id.btnEliminar)
    }
}