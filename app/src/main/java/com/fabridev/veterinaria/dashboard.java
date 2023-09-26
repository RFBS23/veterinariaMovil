package com.fabridev.veterinaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends Activity {
    CardView Cardpersonas, Cardanimales, Cardrazas, Cardmascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        loadUI();
        Cardpersonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(vistapersonas.class);
            }
        });
    }

    private void loadUI(){
        Cardpersonas = findViewById(R.id.Cardpersonas);
        Cardanimales = findViewById(R.id.Cardanimales);
        Cardrazas = findViewById(R.id.Cardrazas);
        Cardmascotas = findViewById(R.id.Cardmascotas);
    }

    private void abrirActivity(Class nameActivity) {
        Intent intent = new Intent(getApplicationContext(), nameActivity);
        startActivity(intent);
    }
}