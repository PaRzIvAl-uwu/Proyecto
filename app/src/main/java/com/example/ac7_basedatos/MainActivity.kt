package com.example.ac7_basedatos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnIniciar: Button

    private lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)

        etEmail = findViewById<EditText>(R.id.etEmail)
        etPassword = findViewById<EditText>(R.id.etPassword)
        btnIniciar = findViewById<Button>(R.id.btnIniciar)

        dbHelper = DatabaseHelper(this)


        //crear evento para iniciar sesion
        btnIniciar.setOnClickListener {
            //TODO: agregar el metodo de inicio de sesion
            iniciarSesion()
        }

        //crear evento para ir a otra pantalla
        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, Activity_Register::class.java)
            startActivity(intent)
        }

    }

    private fun iniciarSesion(){

        val correo = etEmail.text.toString()
        val pass = etPassword.text.toString()

        if (correo.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = dbHelper.inciarSesion(correo, pass)
        if(resultado){
            Toast.makeText(this, "sesion iniciada", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "error al iniciar sesion", Toast.LENGTH_SHORT).show()
        }
    }

}