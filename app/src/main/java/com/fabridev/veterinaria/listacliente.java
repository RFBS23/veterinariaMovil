package com.fabridev.veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listacliente extends Activity {
    ListView listasclientes;
    FloatingActionButton agregar;

    private List<String> dataList = new ArrayList<>();
    private List<Integer> dataID = new ArrayList<>();
    private customAdapter adapter;
    final String URL = "http://192.168.1.36/veterinariaMovil/controllers/clientes.controllers.php";
    private String[] opciones = {"Editar", "Eliminar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacliente);

        loadUI();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(registrocliente.class);
            }
        });
    }

    private void loadUI(){
        listasclientes = findViewById(R.id.listasclientes);
        agregar = findViewById(R.id.agregar);
    }

    private void abrirActivity(Class nameActivity) {
        Intent intent = new Intent(getApplicationContext(), nameActivity);
        startActivity(intent);
    }
}