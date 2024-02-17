package com.example.r2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView lvDB;
    private List<Produs> listaPrimita = new ArrayList<>();
    private List<Produs> listaPrincipala = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lvDB=findViewById(R.id.lvDB);
        ArrayAdapter<Produs>adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, listaPrincipala);
        lvDB.setAdapter(adapter);

        Intent intent=getIntent();
        listaPrimita= (List<Produs>) intent.getSerializableExtra("lista");
        if(listaPrimita!=null)
        {
            listaPrincipala.addAll(listaPrimita);
            adapter.notifyDataSetChanged();
        }

        Intent intent1=getIntent();
        listaPrimita= (List<Produs>) intent1.getSerializableExtra("lista2");
        if(listaPrimita!=null)
        {
            listaPrincipala.addAll(listaPrimita);
            adapter.notifyDataSetChanged();
        }



        lvDB.setOnItemClickListener((adapterView, view, i, l)->{

            Produs produs1 = listaPrincipala.get(i);
            ArrayAdapter<Produs>adapter1= (ArrayAdapter<Produs>) lvDB.getAdapter();
            listaPrincipala.remove(produs1);

            ProdusDB produsDB = ProdusDB.getInstance(getApplicationContext());
            produsDB.getProdusDAO().deleteProdus(produs1);

            adapter1.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"S-a sters din BD", Toast.LENGTH_LONG).show();

        });

    }
}