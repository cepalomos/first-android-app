package com.losextraditados.appproductos.src

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.losextraditados.appproductos.R

class MenuActivity : AppCompatActivity() {

    private lateinit var btnCreate: Button
    private lateinit var btnVer: Button
    private lateinit var btnOut: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnCreate = findViewById(R.id.btnCrearProductos)
        btnOut = findViewById(R.id.btnSalir)
        btnVer = findViewById(R.id.btnVerProductos)

        btnCreate.setOnClickListener(){
            viewCreate()
        }

        btnOut.setOnClickListener(){
            viewInitial()
        }

        btnVer.setOnClickListener(){
            viewProducts()
        }
    }

    private fun viewProducts() {
        val intent = Intent(this@MenuActivity, ProductsActivity::class.java)
        startActivity(intent)
    }

    private fun viewInitial() {
        val intent = Intent(this@MenuActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun viewCreate() {
        val intent = Intent(this@MenuActivity,CreateProductActivity::class.java)
        startActivity(intent)
    }

}