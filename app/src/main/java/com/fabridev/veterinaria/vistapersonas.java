package com.fabridev.veterinaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class vistapersonas extends AppCompatActivity {
    CardView CardRegistrar, Cardlista, Cardbuscar, Cardeliminar;
    Button vermas1, vermas2, vermas3, vermas4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistapersonas);
        loadUI();
        CardRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(registrocliente.class);
            }
        });
        vermas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(registrocliente.class);
            }
        });

        Cardlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(listacliente.class);
            }
        });
        vermas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(listacliente.class);
            }
        });

        Cardbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(buscarcliente.class);
            }
        });
        vermas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(buscarcliente.class);
            }
        });

        Cardeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(eliminarcliente.class);
            }
        });
        vermas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(eliminarcliente.class);
            }
        });
    }

    private void loadUI(){
        CardRegistrar = findViewById(R.id.CardRegistrar);
        Cardlista = findViewById(R.id.Cardlista);
        Cardbuscar = findViewById(R.id.Cardbuscar);
        Cardeliminar = findViewById(R.id.Cardeliminar);
        vermas1 = findViewById(R.id.vermas1);
        vermas2 = findViewById(R.id.vermas2);
        vermas3 = findViewById(R.id.vermas3);
        vermas4 = findViewById(R.id.vermas4);
    }

    private void abrirActivity(Class nameActivity) {
        Intent intent = new Intent(getApplicationContext(), nameActivity);
        startActivity(intent);
    }
}