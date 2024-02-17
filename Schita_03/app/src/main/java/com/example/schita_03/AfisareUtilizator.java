package com.example.schita_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AfisareUtilizator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_utilizator);

        Button inchide = findViewById(R.id.btnInchidere);

        TextView tvNume = findViewById(R.id.tvNume);
        TextView tvParola = findViewById(R.id.tvParola);

        Utilizator utilizatorPrimit = (Utilizator) getIntent().getSerializableExtra("utilizator");

        tvNume.setText(utilizatorPrimit.getNume());
        tvParola.setText(utilizatorPrimit.getParola());

        inchide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}