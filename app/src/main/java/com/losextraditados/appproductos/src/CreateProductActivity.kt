package com.losextraditados.appproductos.src

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.data.CreateProductData
import com.losextraditados.appproductos.src.data.ProductsItemData
import com.losextraditados.appproductos.src.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProductActivity : AppCompatActivity() {

    private lateinit var btnCrear: MaterialButton
    private lateinit var btnCancelar: MaterialButton
    private lateinit var teName: TextInputEditText
    private lateinit var tePrice: TextInputEditText
    private lateinit var teType: TextInputEditText
    private lateinit var teDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
        initComponents()
        initListener()
    }

    private fun initListener() {
        btnCrear.setOnClickListener {
            if (validateData()) {
                val newProductData = CreateProductData(
                    teName.text.toString(),
                    tePrice.text.toString().toInt(),
                    teDescription.text.toString(),
                    teType.text.toString()
                )
                postProduct(newProductData)
            } else
                Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
        }
        btnCancelar.setOnClickListener {
            this@CreateProductActivity.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun validateData(): Boolean {
        var camposValidos = true

        val campo1 = teName.text.toString()
        if (campo1.isEmpty()) {
            teName.error = "Campo obligatorio"
            camposValidos = false
        }

        val campo2 = tePrice.text.toString()
        if (campo2.isEmpty()) {
            tePrice.error = "Campo obligatorio"
            camposValidos = false
        }


        val campo3 = teType.text.toString()
        if (campo3.isEmpty()) {
            teType.error = "Campo obligatorio"
            camposValidos = false
        }

        val campo4 = teDescription.text.toString()
        if (campo4.isEmpty()) {
            teDescription.error = "Campo obligatorio"
            camposValidos = false
        }
        return camposValidos
    }

    private fun postProduct(newProductData: CreateProductData) {
        val api = RetrofitClient.postProducts()
        val call: Call<ProductsItemData> = api.postProduct(newProductData)
        call.enqueue(object : Callback<ProductsItemData> {
            override fun onResponse(
                call: Call<ProductsItemData>,
                response: Response<ProductsItemData>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CreateProductActivity,
                        "El producto ${response.body()?.name} ha sido creado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent= Intent(this@CreateProductActivity,MenuActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(
                        this@CreateProductActivity,
                        "Error al crear el producto en el servidor",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ProductsItemData>, t: Throwable) {
                Toast.makeText(
                    this@CreateProductActivity,
                    "Error al crear el producto",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun initComponents() {
        btnCrear = findViewById(R.id.createProduct)
        btnCancelar = findViewById(R.id.cancelButton)
        teName = findViewById(R.id.productName)
        tePrice = findViewById(R.id.productPrice)
        teType = findViewById(R.id.productType)
        teDescription = findViewById(R.id.productDescription)
    }


}