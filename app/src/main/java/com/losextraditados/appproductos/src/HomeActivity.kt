package com.losextraditados.appproductos.src

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.losextraditados.appproductos.R
import com.losextraditados.appproductos.src.data.LoginData
import com.losextraditados.appproductos.src.reponses.ApiResLogin
import com.losextraditados.appproductos.src.service.ApiSerLogin
import com.losextraditados.appproductos.src.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var user : TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var apiService: ApiSerLogin
    private lateinit var loginData: LoginData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        user = findViewById(R.id.username_edit_text)
        password = findViewById(R.id.password_edit_text)
        btnLogin = findViewById(R.id.login_button)

        btnLogin.setOnClickListener(){
            if (user.text.toString().trim().isEmpty() || password.text.toString().trim().isEmpty()){
                Toast.makeText(this, "Se requiere ambos campos", Toast.LENGTH_SHORT).show()
            }else{

                apiService = RetrofitClient.Login()

                loginData = LoginData(user.text.toString().trim(),password.text.toString().trim())

                loginUser(loginData)
            }
        }

    }

    private fun loginUser(loginData: LoginData) {
        val call: Call<ApiResLogin> = apiService.loginUser(loginData)

        // Ejecuta la llamada asíncrona
        call.enqueue(object : Callback<ApiResLogin> {
            override fun onResponse(call: Call<ApiResLogin>, response: Response<ApiResLogin>) {
                if (response.isSuccessful) {
                    // Procesa la respuesta exitosa del servidor
                    val message = response.body()?.message ?: ""

                    // Muestra un mensaje de éxito al usuario
                    showToast("Inicio de sesión exitoso: $message")

                    // Aquí puedes manejar la lógica de lo que sucede después del inicio de sesión exitoso
                    val intent = Intent(this@HomeActivity, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    // Procesa el error del servidor
                    Log.e("LoginError", "Error en la solicitud de inicio de sesión")
                    // Muestra un mensaje de error al usuario
                    showToast("Error en el inicio de sesión")
                }
            }

            override fun onFailure(call: Call<ApiResLogin>, t: Throwable) {
                // Procesa errores de conexión
                Log.e("LoginError", "Error de conexión", t)
                // Muestra un mensaje de error al usuario
                showToast("Error de conexión")

            }
        })
    }
    private fun showToast(message: String) {
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_SHORT).show()
    }
}