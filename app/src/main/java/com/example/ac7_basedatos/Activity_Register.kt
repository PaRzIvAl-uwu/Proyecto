package com.example.ac7_basedatos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_Register : AppCompatActivity() {

    private lateinit var etUser : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnRegistrar : Button

    private lateinit var dbHelper : DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        etUser = findViewById(R.id.etUser)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        dbHelper = DatabaseHelper(this)

        //crear elemento para registrar a un usuario
        btnRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario(){
        //obtener la informacion que escribe el usuario
        val nombre = etUser.text.toString().trim()
        val correo = etEmail.text.toString().trim()
        val pass = etPassword.text.toString().trim()

        //validar que el usuario llene los campos
        if(nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Completa todos los campos uwu", Toast.LENGTH_SHORT).show()
            return
        }
        val resultado = dbHelper.registrarUsuario(nombre, correo, pass)

        if(resultado){
            //si el resultado es verdadero
            Toast.makeText(this, "Registro exitoso :))", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
        }
    }
}