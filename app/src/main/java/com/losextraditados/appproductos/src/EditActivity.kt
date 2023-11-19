package com.losextraditados.appproductos.src

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.data.ProductsItemData
import com.losextraditados.appproductos.src.data.UpdateProduct
import com.losextraditados.appproductos.src.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    private lateinit var btnCancelar: MaterialButton
    private lateinit var btnActualizar: MaterialButton
    private lateinit var teNombre: TextInputEditText
    private lateinit var tePrecio: TextInputEditText
    private lateinit var teTipoProducto: TextInputEditText
    private lateinit var teDescripcion: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val productData: ProductsItemData =
            intent.getParcelableExtra(ProductsActivity.PRODUCT) ?: ProductsItemData(
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
        try {
            initIU(productData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initIU(productData: ProductsItemData?) {
        productData?.let {
            teNombre.setText(it.name)
            tePrecio.setText(it.price.toString())
            teTipoProducto.setText(it.productType)
            teDescripcion.setText(it.description)
        }
    }



    private fun initComponents() {
        btnActualizar = findViewById(R.id.updateButton)
        btnCancelar = findViewById(R.id.cancelButton)
        teNombre = findViewById(R.id.productName)
        tePrecio = findViewById(R.id.productPrice)
        teTipoProducto = findViewById(R.id.productType)
        teDescripcion = findViewById(R.id.productDescription)
    }

    private fun initListeners(productData: ProductsItemData) {
        btnActualizar.setOnClickListener {
            if (teNombre.text.toString().isNullOrEmpty() && tePrecio.text.toString()
                    .isNullOrEmpty() && teDescripcion.text.toString()
                    .isNullOrEmpty() && teTipoProducto.text.toString().isNullOrEmpty()
            ) {
                Toast.makeText(this, "LLenar min 1 de los campos", Toast.LENGTH_SHORT).show()
            } else {
                val updateData = UpdateProduct(
                    productData.id,
                    teNombre.text.toString(),
                    tePrecio.text.toString().toInt(),
                    teDescripcion.text.toString(),
                    teTipoProducto.text.toString()
                )
                val api = RetrofitClient.putProducts()
                val call: Call<ProductsItemData> = api.putProduct(updateData)
                call.enqueue(object : Callback<ProductsItemData> {
                    override fun onResponse(
                        call: Call<ProductsItemData>,
                        response: Response<ProductsItemData>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@EditActivity,
                                "Actualizado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@EditActivity,ProductsActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(
                                this@EditActivity,
                                "Upss algo fallo",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ProductsItemData>, t: Throwable) {
                        Toast.makeText(
                            this@EditActivity,
                            "Upss algo fallo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
        btnCancelar.setOnClickListener { this@EditActivity.onBackPressedDispatcher.onBackPressed() }
    }
}