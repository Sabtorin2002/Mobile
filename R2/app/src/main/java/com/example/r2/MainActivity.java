package com.example.r2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Produs> listaProduse = new ArrayList<>();
    private ListView lvProduse;
    private final static String adresaJson="https://138.68.47.227/b/U938";

    private EditText etID;
    private EditText etDenumire;
    private EditText etPret;

    private Button btnSalvare;
    private Button btnIncarca;

    private Button btnIncarca2;

    private List<Produs>listaTrimisa=new ArrayList<>();

    private List<Produs>listaTrimisa2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProduse=findViewById(R.id.lvProduse);
        ArrayAdapter<Produs>adapter=new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, listaProduse);
        lvProduse.setAdapter(adapter);

        incarcareProduseHttps();

        etID=findViewById(R.id.etID);
        etDenumire=findViewById(R.id.etDenumire);
        etPret=findViewById(R.id.etPret);
        btnIncarca=findViewById(R.id.btnIncarca);
        btnSalvare=findViewById(R.id.btnSalvare);
        btnIncarca2=findViewById(R.id.btnIncarca2);

        btnSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = Long.parseLong(etID.getText().toString());
                String denumire =etDenumire.getText().toString();
                float pret = Float.parseFloat(etPret.getText().toString());

                Produs produs =  new Produs(id,denumire,pret);
                ProdusDB produsDB = ProdusDB.getInstance(getApplicationContext());
                produsDB.getProdusDAO().insertProdus(produs);

                Toast.makeText(getApplicationContext(),"S-a adaugat in BD", Toast.LENGTH_LONG).show();

            }
        });

        btnIncarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdusDB produsDB = ProdusDB.getInstance(getApplicationContext());
                listaTrimisa.addAll(produsDB.getProdusDAO().selectAll(0));
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("lista", (Serializable) listaTrimisa);
                setResult(RESULT_OK, intent);
                startActivity(intent);

            }
        });


        btnIncarca2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdusDB produsDB=ProdusDB.getInstance(getApplicationContext());

                listaTrimisa2.addAll(produsDB.getProdusDAO().selectAll(0));
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("lista2", (Serializable) listaTrimisa2);
                setResult(RESULT_OK, intent);
                startActivity(intent);


            }
        });
    }


    private void getProduseFromJson(String rezultat){
        listaProduse.addAll(ProdusParser.fromJson(rezultat));//folosesc functia publica din parser
        ArrayAdapter<Produs> adapter = (ArrayAdapter<Produs>) lvProduse.getAdapter();//modific adaptorul pentru lvProduse
        adapter.notifyDataSetChanged();//fac schimbarile
    }

    private void incarcareProduseHttps(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager httpsManager=new HttpsManager(adresaJson);
                String rezultat =httpsManager.process();
                new Handler(getMainLooper()).post(()->
                        getProduseFromJson(rezultat));
            }
        };
        thread.start();
    }

}