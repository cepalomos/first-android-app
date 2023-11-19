package com.losextraditados.appproductos.src.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://34.42.40.85:3001/v1/"

    private var retrofit: Retrofit? = null

    fun Login(): ApiSerLogin {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiSerLogin::class.java)
    }

    fun getProducts(): ApiSerProduct {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiSerProduct::class.java)
    }

    fun deleteProducts(): ApiSerDeleteProduct{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiSerDeleteProduct::class.java)
    }

    fun putProducts(): ApiSerUpdate{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiSerUpdate::class.java)
    }
    fun postProducts(): ApiSerCreate{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiSerCreate::class.java)
    }
}