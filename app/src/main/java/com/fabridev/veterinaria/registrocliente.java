package com.fabridev.veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class registrocliente extends Activity {

    EditText registronombres, registroapellidos, registrodni, registroclave;
    Button btnregistrar;
    String nombres, apellidos, dni, claveacceso;
    final String URL = "http://192.168.1.36/veterinariaMovil/controllers/clientes.controllers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrocliente);
        loadUI();
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarControles();
            }
        });
    }

    private void loadUI() {
        registronombres = findViewById(R.id.registronombres);
        registroapellidos = findViewById(R.id.registroapellidos);
        registrodni = findViewById(R.id.registrodni);
        registroclave = findViewById(R.id.registroclave);
        btnregistrar = findViewById(R.id.btnregistrar);
    }
    private void resetUI() {
        registronombres.setText("");
        registroapellidos.setText("");
        registrodni.setText("");
        registroclave.setText("");
    }

    private void validarControles() {
        nombres = registronombres.getText().toString().trim();
        apellidos = registroapellidos.getText().toString().trim();
        dni = registrodni.getText().toString().trim();
        claveacceso = registroclave.getText().toString().trim();
        
        if (nombres.isEmpty()) {
            registronombres.setError("Ingresa tu nombre");
        } else if (apellidos.isEmpty()) {
            registroapellidos.setError("Ingresa tus Apellidos");
        } else if (dni.isEmpty()){
            registrodni.setError("Completa este campo");
        } else if (claveacceso.isEmpty()) {
            registroclave.setError("Ingresa una contraseña");
        } else {
            mostrarDialogoRegistro();
        }
    }

    private void mostrarDialogoRegistro(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Veterinaria");
        dialog.setMessage("¿Se estan Validando los datos Ingresados?");
        dialog.setCancelable(false);

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                registrarCliente();
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private void registrarCliente(){
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("")){
                    resetUI();
                    registronombres.requestFocus();
                    Toast.makeText(getApplicationContext(), "El Cliente se Registro correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("operacion", "agregar");
                parametros.put("nombres", nombres);
                parametros.put("apellidos", apellidos);
                parametros.put("dni", dni);
                parametros.put("claveacceso", claveacceso);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}