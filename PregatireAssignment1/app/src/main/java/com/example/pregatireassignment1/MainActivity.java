package com.example.pregatireassignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ListView lvListaAlimente;
    List<Aliment> listaAlimente = new ArrayList<>();
    //List<Aliment> listaAlimenteTXT = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scriereInFisierText("fisier.txt","Hello world from text file");
        scriereInFisierText("listaalimentescris.csv","1,ciocolata,9.99" +"\n"+
                "2,portocale,4.99"+"\n"+ "3,clementine,5.99"+"\n"+"4,sunca,12.99"+"\n"+
                "5,iaurt,2.49");

        //tv = findViewById(R.id.tv);
        //tv.setText(citireDinFisierText("fisier.txt"));
        //ExtractTXT("listaalimente.txt");
        ExtractTXT("listaalimentescris.csv");
        lvListaAlimente = findViewById(R.id.lvListaAlimente);
        AlimentAdapter alimentAdapter = new AlimentAdapter(getApplicationContext(),
                R.layout.view_alimente, listaAlimente, getLayoutInflater()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position,convertView,parent);
                Aliment aliment1 = listaAlimente.get(position);
                TextView tvPret = view.findViewById(R.id.tvPret);
                if(aliment1.getPret()>5){
                 tvPret.setTextColor(Color.RED);
                }else {
                    tvPret.setTextColor(Color.BLUE);
                }
                return view;
            }
        };
        lvListaAlimente.setAdapter(alimentAdapter);

        //adaugare configurari
//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putInt("time", 10);
//        //editor.remove("time");
//        editor.apply();
//
//        //citire configurari din shared pref
//        int time = sharedPreferences.getInt("time",0);
//        Toast.makeText(this, String.valueOf(time),Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("time",15);
        //editor.remove("time");
        editor.apply();

        //ultima accesare+nume
        SharedPreferences.Editor editorU = sharedPreferences.edit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String ultimaAccesare = simpleDateFormat.format(new Date());
        editorU.putString("ultima", ultimaAccesare);
        editorU.apply();

        String output = sharedPreferences.getString("ultima", "data");
        Toast.makeText(getApplicationContext(),"Ultima accesare:"+output+" Sabin",Toast.LENGTH_LONG).show();
        floatingActionButton = findViewById(R.id.floatingActionButton);
        int size= listaAlimente.size();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("lungimeaListei",size);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });
    }


    private String ExtractTXT(String numeFisier){

        String linie="";
        String[] elemente;
        String rezultat="";
        try {
            //InputStream is = getResources().openRawResource(R.raw.listaalimente);
            InputStream is = getApplicationContext().openFileInput(numeFisier);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            while ((linie=reader.readLine())!=null){
                elemente=linie.split(",");

                int id = Integer.parseInt(elemente[0].toString());
                String nume = elemente[1].toString();
                float pret = Float.parseFloat(elemente[2].toString());

                Aliment aliment =  new Aliment(id, nume, pret);
                listaAlimente.add(aliment);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rezultat;
    }

//    private String citireDinFisierText(String numeFisier){
//        String textCititDinFisier="";
//        try {
//            //InputStream is = getResources().openRawResource(R.raw.f);
//            InputStream is = getResources().openRawResource(R.raw.listaalimente);
//            //InputStream is = getApplicationContext().openFileInput(numeFisier);
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader reader =  new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String linie = "";
//            while((linie=reader.readLine())!=null){
//                sb.append("\n").append(linie);
//            }
//
//            textCititDinFisier = sb.toString();
//            reader.close();
//            isr.close();
//            is.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return textCititDinFisier;
//    }
    private void scriereInFisierText(String numeFisier, String text){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(getApplicationContext()
                    .openFileOutput(numeFisier,MODE_PRIVATE));
            writer.write(text);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}