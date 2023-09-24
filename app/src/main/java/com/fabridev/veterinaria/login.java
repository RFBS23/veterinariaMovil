package com.fabridev.veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends Activity {
    EditText logindni, loginpassword;
    ImageView claveicono;
    Button logininiciar;
    private boolean vercontraseña = false;
    String dni, claveacceso;

    String URL = "http://192.168.1.39/veterinariaMovil/controllers/clientes.controllers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadUI();
        //mostramos y ocultamos la contraseña
        claveicono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarcontraseña(v);
            }
        });

        logininiciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaControles();
            }
        });
    }

    private void loadUI(){
        dni = claveacceso = ""; // ingresamos el dni y la clave en unaácadena vacia
        logindni = findViewById(R.id.logindni);
        loginpassword = findViewById(R.id.loginpassword);
        claveicono = findViewById(R.id.claveicono);
        logininiciar = findViewById(R.id.logininiciar);
    }

    private void abrirActivity(Class nameActivity) {
        Intent intent = new Intent(getApplicationContext(), nameActivity);
        startActivity(intent);
    }

    //mostrar contraseña
    private void mostrarcontraseña(View v){
        if (vercontraseña) {
            vercontraseña = false;
            loginpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            claveicono.setImageResource(R.raw.eyes_show);
        } else {
            vercontraseña = true;
            loginpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            claveicono.setImageResource(R.raw.eyes_hide);
        }
        loginpassword.setSelection(loginpassword.length());
    }

    private void validaControles(){
        dni = logindni.getText().toString().trim();
        claveacceso = loginpassword.getText().toString().trim();

        if (dni.isEmpty()) {
            logindni.setError("Por Favor Ingresa tu dni");
        } else if (claveacceso.isEmpty()) {
            loginpassword.setError("Ingrese su contraseña");
        } else {
            abrirActivity(dashboard.class);
        }
    }

}