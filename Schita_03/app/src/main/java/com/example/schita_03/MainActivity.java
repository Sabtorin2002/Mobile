package com.example.schita_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Utilizator utilizator = new Utilizator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSalvare = findViewById(R.id.btnSalvare);

        btnSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfisareUtilizator.class);

                EditText etNume = findViewById(R.id.etNume);
                EditText etParola = findViewById(R.id.etParola);

                utilizator.setNume(etNume.getText().toString());
                utilizator.setParola(etParola.getText().toString());

                intent.putExtra("utilizator", utilizator);

                startActivityForResult(intent, 100);
            }
        });
    }


}